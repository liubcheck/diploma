package liubomyr.stepanenko.lessonservice.dto.response;

import java.util.Map;
import lombok.Data;

@Data
public class UserStatsDto {
    private String userEmail;
    private Integer totalScore;
    private Double averageScore;
    private Double averageAttemptsNumber;
    private Integer bestScore;
    private Integer worstScore;
    private Map<String, Integer> testCountsByDay;
    private Map<String, Integer> topTenUsersData;
}
