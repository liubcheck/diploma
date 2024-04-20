package liubomyr.stepanenko.userservice.service;

import jakarta.persistence.EntityNotFoundException;
import liubomyr.stepanenko.userservice.dto.request.UserLoginRequestDto;
import liubomyr.stepanenko.userservice.dto.request.UserRegistrationRequestDto;
import liubomyr.stepanenko.userservice.dto.response.UserDto;
import liubomyr.stepanenko.userservice.dto.response.UserLoginResponseDto;
import liubomyr.stepanenko.userservice.exception.RegistrationException;
import liubomyr.stepanenko.userservice.mapper.UserMapper;
import liubomyr.stepanenko.userservice.model.Role;
import liubomyr.stepanenko.userservice.model.User;
import liubomyr.stepanenko.userservice.model.type.RoleName;
import liubomyr.stepanenko.userservice.repository.RoleRepository;
import liubomyr.stepanenko.userservice.repository.UserRepository;
import liubomyr.stepanenko.userservice.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public UserDto register(UserRegistrationRequestDto request)
            throws RegistrationException {
        if (userRepository.findByUsernameOrEmail(request.getEmail()).isPresent()) {
            throw new RegistrationException(
                    String.format("The user with the email %s is already registered",
                            request.getEmail()));
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.USER)
                .orElseThrow(() -> new IllegalStateException("USER role not found"));
        user.setRole(userRole);

        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public UserLoginResponseDto authenticate(UserLoginRequestDto request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLoginData(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        String token = jwtUtil.generateToken(authentication.getName());
        return new UserLoginResponseDto(userMapper.toDto(user), token);
    }

    public UserDto findByUsernameOrEmail(String data) {
        User user = userRepository.findByUsernameOrEmail(data).orElseThrow(
                () -> new EntityNotFoundException("User not found with such identifier " + data)
        );
        return userMapper.toDto(user);
    }

    public void logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
