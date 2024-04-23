package liubomyr.stepanenko.lessonservice.controller;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import liubomyr.stepanenko.lessonservice.dto.request.ProgressRequestDto;
import liubomyr.stepanenko.lessonservice.dto.response.ProgressDto;
import liubomyr.stepanenko.lessonservice.dto.response.UserDto;
import liubomyr.stepanenko.lessonservice.service.impl.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
public class ProgressController {
    private final ProgressService progressService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ProgressDto saveUserProgress(Authentication authentication,
                                        @RequestBody @Valid ProgressRequestDto requestDto) {
        UserDto userDto = (UserDto) authentication.getPrincipal();
        return progressService.save(userDto.getEmail(), requestDto);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<ProgressDto> getAllUserProgress(Authentication authentication) {
        UserDto userDto = (UserDto) authentication.getPrincipal();
        return progressService.getAllByUserEmail(userDto.getEmail());
    }

    @GetMapping("/total-score")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Integer getUserTotalScore(Authentication authentication) {
        UserDto userDto = (UserDto) authentication.getPrincipal();
        return progressService.getUserTotalScore(userDto.getEmail());
    }

    @GetMapping("/average-score")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Double getUserAverageScore(Authentication authentication) {
        UserDto userDto = (UserDto) authentication.getPrincipal();
        return progressService.getUserAverageScore(userDto.getEmail());
    }

    @GetMapping("/average-attempts-number")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Double getAverageLessonAttemptsNumber(Authentication authentication) {
        UserDto userDto = (UserDto) authentication.getPrincipal();
        return progressService.getAverageLessonAttemptsNumber(userDto.getEmail());
    }

    @GetMapping("/best-score")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Integer getBestScore(Authentication authentication) {
        UserDto userDto = (UserDto) authentication.getPrincipal();
        return progressService.getBestScoreByUserEmail(userDto.getEmail());
    }

    @GetMapping("/worst-score")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Integer getWorstScore(Authentication authentication) {
        UserDto userDto = (UserDto) authentication.getPrincipal();
        return progressService.getWorstScoreByUserEmail(userDto.getEmail());
    }

    @GetMapping("/tests-count-by-day")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Map<String, Integer> getTestCountsByDay(Authentication authentication) {
        UserDto userDto = (UserDto) authentication.getPrincipal();
        return progressService.getTestCountsByDay(userDto.getEmail());
    }

    @GetMapping("/top-10")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public Map<String, Integer> getTopTenUsernamesByScore() {
        return progressService.getTopTenUsersByScore();
    }
}
