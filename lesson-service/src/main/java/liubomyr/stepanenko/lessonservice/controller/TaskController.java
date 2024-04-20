package liubomyr.stepanenko.lessonservice.controller;

import jakarta.validation.Valid;
import java.util.List;
import liubomyr.stepanenko.lessonservice.dto.request.TaskRequestDto;
import liubomyr.stepanenko.lessonservice.dto.response.TaskDto;
import liubomyr.stepanenko.lessonservice.service.impl.TaskService;
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
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public TaskDto save(@RequestBody @Valid TaskRequestDto requestDto) {
        return taskService.save(requestDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public TaskDto getById(@PathVariable Long id) {
        return taskService.getById(id);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public List<TaskDto> getAll() {
        return taskService.getAll();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public TaskDto update(@PathVariable Long id,
                            @RequestBody @Valid TaskRequestDto requestDto) {
        return taskService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Long deleteById(@PathVariable Long id) {
        taskService.deleteById(id);
        return id;
    }

    @GetMapping("/lesson/{lessonId}")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public List<TaskDto> getRandomizedTasks(@PathVariable Long lessonId) {
        return taskService.getRandomizedTasks(lessonId);
    }
}
