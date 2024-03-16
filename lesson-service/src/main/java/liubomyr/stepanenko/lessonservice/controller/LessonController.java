package liubomyr.stepanenko.lessonservice.controller;

import jakarta.validation.Valid;
import java.util.List;
import liubomyr.stepanenko.lessonservice.dto.request.LessonRequestDto;
import liubomyr.stepanenko.lessonservice.dto.response.LessonDto;
import liubomyr.stepanenko.lessonservice.service.impl.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public LessonDto save(@RequestBody @Valid LessonRequestDto requestDto) {
        return lessonService.save(requestDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public LessonDto getById(@PathVariable Long id) {
        return lessonService.getById(id);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public List<LessonDto> getAll() {
        return lessonService.getAll();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public LessonDto update(@PathVariable Long id,
                            @RequestBody @Valid LessonRequestDto requestDto) {
        return lessonService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Long deleteById(@PathVariable Long id) {
        lessonService.deleteById(id);
        return id;
    }
}
