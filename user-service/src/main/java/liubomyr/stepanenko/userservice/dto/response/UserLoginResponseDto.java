package liubomyr.stepanenko.userservice.dto.response;

public record UserLoginResponseDto(UserDto user, String token) {
}
