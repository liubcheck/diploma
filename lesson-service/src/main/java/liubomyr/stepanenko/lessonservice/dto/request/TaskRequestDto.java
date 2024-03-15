package liubomyr.stepanenko.lessonservice.dto.request;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;

@Data
public class TaskRequestDto {
    @NotNull
    private String question;
    @NotNull
    private String taskType;
    private List<VariantRequestDto> variants;
    private String rightAnswer;
}
