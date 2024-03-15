package liubomyr.stepanenko.lessonservice.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class TaskDto {
    private Long id;
    private String question;
    private String taskType;
    private List<VariantDto> variants;
    private String rightAnswer;
}