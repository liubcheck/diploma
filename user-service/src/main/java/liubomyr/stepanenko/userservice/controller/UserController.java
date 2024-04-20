package liubomyr.stepanenko.userservice.controller;

import jakarta.validation.Valid;
import liubomyr.stepanenko.userservice.dto.request.UserLoginRequestDto;
import liubomyr.stepanenko.userservice.dto.request.UserRegistrationRequestDto;
import liubomyr.stepanenko.userservice.dto.response.UserDto;
import liubomyr.stepanenko.userservice.dto.response.UserLoginResponseDto;
import liubomyr.stepanenko.userservice.exception.RegistrationException;
import liubomyr.stepanenko.userservice.mapper.UserMapper;
import liubomyr.stepanenko.userservice.model.User;
import liubomyr.stepanenko.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/hello")
    @PreAuthorize("hasRole('ADMIN')")
    public String hello() {
        return "Hello World!";
    }

    @PostMapping("/registration")
    public UserDto register(@RequestBody @Valid UserRegistrationRequestDto request)
            throws RegistrationException {
        return userService.register(request);
    }

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto request) {
        return userService.authenticate(request);
    }

    @GetMapping("/find")
    public UserDto findByUsernameOrEmail(@RequestParam(value = "data") String data) {
        return userService.findByUsernameOrEmail(data);
    }

    @GetMapping("/me")
    public UserDto findCurrentUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return userMapper.toDto(user);
    }

    @PostMapping("/logout")
    public void logoutUser() {
        userService.logout();
    }
}
