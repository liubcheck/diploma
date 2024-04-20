package liubomyr.stepanenko.lessonservice.dto.response;

import lombok.Data;

@Data
public class ProgressDto {
    private Long id;
    private Long lessonId;
    private String userEmail;
    private Boolean isCompleted;
    private Integer score;
}
