package liubomyr.stepanenko.lessonservice.dto.request;

import lombok.Data;

@Data
public class ProgressRequestDto {
    private Long lessonId;
    private Integer score;
}
