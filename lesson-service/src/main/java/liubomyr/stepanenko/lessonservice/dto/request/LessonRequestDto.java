package liubomyr.stepanenko.lessonservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;

@Data
public class LessonRequestDto {
    @NotNull
    private Integer grade;
    @NotBlank
    private String title;
    private List<Long> taskIds;
}
