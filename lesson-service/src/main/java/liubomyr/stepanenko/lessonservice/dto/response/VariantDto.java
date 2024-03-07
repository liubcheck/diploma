package liubomyr.stepanenko.lessonservice.dto.response;

import lombok.Data;

@Data
public class VariantDto {
    private Long id;
    private String value;
    private Boolean isRight;
}
