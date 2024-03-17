package liubomyr.stepanenko.lessonservice.mapper.impl;

import java.util.List;
import liubomyr.stepanenko.lessonservice.dto.request.TaskRequestDto;
import liubomyr.stepanenko.lessonservice.dto.response.TaskDto;
import liubomyr.stepanenko.lessonservice.mapper.BasicMapper;
import liubomyr.stepanenko.lessonservice.model.Task;
import liubomyr.stepanenko.lessonservice.model.Variant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskMapper implements BasicMapper<Task, TaskRequestDto, TaskDto> {
    private final VariantMapper variantMapper;

    @Override
    public TaskDto toDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setQuestion(task.getQuestion());
        taskDto.setTaskType(task.getTaskType());
        if (task.getVariants() != null && !task.getVariants().isEmpty()) {
            taskDto.setVariants(task.getVariants().stream()
                    .map(variantMapper::toDto)
                    .toList());
        }
        taskDto.setRightAnswer(task.getRightAnswer());
        return taskDto;
    }

    @Override
    public Task toModel(TaskRequestDto taskRequestDto) {
        Task task = new Task();
        updateModelFromDto(task, taskRequestDto);
        return task;
    }

    @Override
    public void updateModelFromDto(Task task, TaskRequestDto taskRequestDto) {
        task.setQuestion(taskRequestDto.getQuestion());
        task.setTaskType(taskRequestDto.getTaskType());
        task.setRightAnswer(taskRequestDto.getRightAnswer());
        if (taskRequestDto.getVariants() != null && !taskRequestDto.getVariants().isEmpty()) {
            List<Variant> variants = taskRequestDto.getVariants().stream()
                    .map(variantMapper::toModel)
                    .toList();
            variants.forEach(variant -> variant.setTask(task));
            task.getVariants().clear();
            task.getVariants().addAll(variants);
        }
    }
}
