package liubomyr.stepanenko.lessonservice.mapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import liubomyr.stepanenko.lessonservice.dto.request.TaskRequestDto;
import liubomyr.stepanenko.lessonservice.dto.response.TaskDto;
import liubomyr.stepanenko.lessonservice.model.Task;
import liubomyr.stepanenko.lessonservice.model.Variant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskMapper {
    private final VariantMapper variantMapper;

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

    public Task toModel(TaskRequestDto taskRequestDto) {
        Task task = new Task();
        updateModelFromDto(task, taskRequestDto);
        return task;
    }

    public void updateModelFromDto(Task task, TaskRequestDto taskRequestDto) {
        task.setQuestion(taskRequestDto.getQuestion());
        task.setTaskType(taskRequestDto.getTaskType());
        task.setRightAnswer(taskRequestDto.getRightAnswer());
        if (taskRequestDto.getVariants() != null && !taskRequestDto.getVariants().isEmpty()) {
            Map<Long, Variant> existingVariants = task.getVariants().stream()
                    .collect(Collectors.toMap(Variant::getId, variant -> variant));
            List<Variant> updatedVariants = taskRequestDto.getVariants().stream()
                    .map(variantDto -> {
                        Variant variant;
                        if (variantDto.getId() != null && existingVariants.containsKey(variantDto.getId())) {
                            variant = existingVariants.get(variantDto.getId());
                        } else {
                            variant = new Variant();
                            variant.setTask(task);
                        }
                        variant.setValue(variantDto.getValue());
                        variant.setIsRight(variantDto.getIsRight());
                        return variant;
                    })
                    .toList();
            task.getVariants().clear();
            task.getVariants().addAll(updatedVariants);
        }
    }
}
