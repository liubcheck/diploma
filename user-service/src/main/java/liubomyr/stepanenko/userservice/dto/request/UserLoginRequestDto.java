package liubomyr.stepanenko.userservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginRequestDto {
    @NotBlank
    private String loginData;
    @NotBlank
    private String password;
}
