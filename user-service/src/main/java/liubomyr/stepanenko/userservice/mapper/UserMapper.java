package liubomyr.stepanenko.userservice.mapper;

import liubomyr.stepanenko.userservice.dto.request.UserRegistrationRequestDto;
import liubomyr.stepanenko.userservice.dto.response.UserDto;
import liubomyr.stepanenko.userservice.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        return userDto;
    }

    public User toModel(UserRegistrationRequestDto requestDto) {
        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setUsername(requestDto.getUsername());
        user.setPassword(requestDto.getPassword());
        return user;
    }
}
