package liubomyr.stepanenko.lessonservice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VariantRequestDto {
    private Long id;
    private Long taskId;
    @NotNull
    private String value;
    @NotNull
    private Boolean isRight;
}
