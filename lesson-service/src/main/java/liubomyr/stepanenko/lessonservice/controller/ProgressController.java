package liubomyr.stepanenko.lessonservice.controller;

import jakarta.validation.Valid;
import java.util.List;
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
}
