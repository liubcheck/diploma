package liubomyr.stepanenko.lessonservice.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class LessonDto {
    private Long id;
    private String subject;
    private Integer grade;
    private String title;
    private List<TaskDto> tasks;
}
