package liubomyr.stepanenko.lessonservice.dto.response;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ProgressDto {
    private Long id;
    private Long lessonId;
    private String userEmail;
    private Boolean isCompleted;
    private LocalDateTime passingDate;
    private Integer score;
}
