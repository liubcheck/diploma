package liubomyr.stepanenko.lessonservice.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import liubomyr.stepanenko.lessonservice.dto.request.ProgressRequestDto;
import liubomyr.stepanenko.lessonservice.dto.response.ProgressDto;
import liubomyr.stepanenko.lessonservice.dto.response.UserDto;
import liubomyr.stepanenko.lessonservice.dto.response.UserStatsDto;
import liubomyr.stepanenko.lessonservice.feign.UserFeignClient;
import liubomyr.stepanenko.lessonservice.mapper.impl.ProgressMapper;
import liubomyr.stepanenko.lessonservice.model.Progress;
import liubomyr.stepanenko.lessonservice.repository.ProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProgressService {
    private final ProgressRepository progressRepository;
    private final ProgressMapper progressMapper;
    private final UserFeignClient userFeignClient;

    public ProgressDto save(String email, ProgressRequestDto requestDto) {
        Progress progress = progressMapper.toModel(requestDto);
        progress.setUserEmail(email);
        return progressMapper.toDto(progressRepository.save(progress));
    }

    public List<ProgressDto> getAllByUserEmail(String email) {
        return progressRepository.findAllByUserEmail(email).stream()
                .map(progressMapper::toDto)
                .toList();
    }

    public Integer getUserTotalScore(String email) {
        return progressRepository.findTotalUserScore(email);
    }

    public Double getUserAverageScore(String email) {
        Double averageScore = progressRepository.findAverageScoreByUserEmail(email);
        if (averageScore == null) {
            return null;
        }
        BigDecimal averageScoreBigDecimal = new BigDecimal(Double.toString(averageScore));
        averageScoreBigDecimal = averageScoreBigDecimal.setScale(2, RoundingMode.HALF_UP);
        return averageScoreBigDecimal.doubleValue();
    }

    public Double getAverageLessonAttemptsNumber(String email) {
        Double averageLessonAttemptsNumber = progressRepository.findAverageLessonAttemptsNumber(email);
        if (averageLessonAttemptsNumber == null) {
            return null;
        }
        BigDecimal averageScoreBigDecimal = new BigDecimal(Double.toString(averageLessonAttemptsNumber));
        averageScoreBigDecimal = averageScoreBigDecimal.setScale(2, RoundingMode.HALF_UP);
        return averageScoreBigDecimal.doubleValue();
    }

    public Integer getBestScoreByUserEmail(String email) {
        return progressRepository.findBestScoreByUserEmail(email);
    }

    public Integer getWorstScoreByUserEmail(String email) {
        return progressRepository.findWorstScoreByUserEmail(email);
    }

    public Map<String, Integer> getTestCountsByDay(String userEmail) {
        List<Object[]> results = progressRepository.countTestsLastWeekByDay(userEmail);
        Map<String, Integer> testCountsByDay = new LinkedHashMap<>();
        for (Object[] result : results) {
            DayOfWeek day = ((Timestamp) result[0]).toLocalDateTime().toLocalDate().getDayOfWeek();
            Integer count = ((Number) result[1]).intValue();
            testCountsByDay.put(day.name(), count);
        }
        return testCountsByDay;
    }

    public Map<String, Integer> getTopTenUsersByScore() {
        List<Object[]> results = progressRepository.findTopTenUsernamesByScore();
        Map<String, Integer> topTenUsers = new LinkedHashMap<>();
        for (Object[] result : results) {
            String userEmail = (String) result[0];
            UserDto user = userFeignClient.findByUsernameOrEmail(userEmail);
            topTenUsers.put(user.getUsername(), ((Number) result[1]).intValue());
        }
        return topTenUsers;
    }

    public UserStatsDto getUserStats(String userEmail) {
        UserStatsDto userStatsDto = new UserStatsDto();
        userStatsDto.setUserEmail(userEmail);
        userStatsDto.setTotalScore(getUserTotalScore(userEmail));
        userStatsDto.setAverageScore(getUserAverageScore(userEmail));
        userStatsDto.setAverageAttemptsNumber(getAverageLessonAttemptsNumber(userEmail));
        userStatsDto.setBestScore(getBestScoreByUserEmail(userEmail));
        userStatsDto.setWorstScore(getWorstScoreByUserEmail(userEmail));
        userStatsDto.setTestCountsByDay(getTestCountsByDay(userEmail));
        userStatsDto.setTopTenUsersData(getTopTenUsersByScore());
        return userStatsDto;
    }
}
