package liubomyr.stepanenko.lessonservice.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import liubomyr.stepanenko.lessonservice.dto.request.TaskRequestDto;
import liubomyr.stepanenko.lessonservice.dto.response.TaskDto;
import liubomyr.stepanenko.lessonservice.mapper.impl.TaskMapper;
import liubomyr.stepanenko.lessonservice.model.Task;
import liubomyr.stepanenko.lessonservice.repository.TaskRepository;
import liubomyr.stepanenko.lessonservice.service.BasicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("taskService")
@RequiredArgsConstructor
public class TaskService implements BasicService<TaskRequestDto, TaskDto> {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    @Transactional
    public TaskDto save(TaskRequestDto taskRequestDto) {
        Task task = taskMapper.toModel(taskRequestDto);
        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    @Transactional
    public TaskDto getById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Task not found with id = " + id)
        );
        return taskMapper.toDto(task);
    }

    @Override
    @Transactional
    public List<TaskDto> getAll() {
        return taskRepository.findAll().stream()
                .map(taskMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public TaskDto update(Long id, TaskRequestDto taskRequestDto) {
        Task taskForUpdate = taskRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Task not found with id = " + id)
        );
        taskMapper.updateModelFromDto(taskForUpdate, taskRequestDto);
        return taskMapper.toDto(taskRepository.save(taskForUpdate));
    }

    @Override
    public Long deleteById(Long id) {
        taskRepository.deleteById(id);
        return id;
    }
}
