package liubomyr.stepanenko.lessonservice.dto.request;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;

@Data
public class LessonRequestDto {
    @NotNull
    private String title;
    private List<Long> taskIds;
}
