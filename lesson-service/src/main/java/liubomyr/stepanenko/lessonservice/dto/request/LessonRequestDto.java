package liubomyr.stepanenko.lessonservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;

@Data
public class LessonRequestDto {
    @NotBlank
    private String subject;
    @NotNull
    private Integer grade;
    @NotBlank
    private String title;
    private List<TaskRequestDto> tasks;
}
