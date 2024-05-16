package liubomyr.stepanenko.lessonservice.mapper.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import liubomyr.stepanenko.lessonservice.dto.request.LessonRequestDto;
import liubomyr.stepanenko.lessonservice.dto.request.TaskRequestDto;
import liubomyr.stepanenko.lessonservice.dto.response.LessonDto;
import liubomyr.stepanenko.lessonservice.mapper.BasicMapper;
import liubomyr.stepanenko.lessonservice.model.Lesson;
import liubomyr.stepanenko.lessonservice.model.Task;
import liubomyr.stepanenko.lessonservice.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LessonMapper implements BasicMapper<Lesson, LessonRequestDto, LessonDto> {
    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;

    @Override
    public LessonDto toDto(Lesson lesson) {
        LessonDto lessonDto = new LessonDto();
        lessonDto.setSubject(lesson.getSubject());
        lessonDto.setId(lesson.getId());
        lessonDto.setGrade(lesson.getGrade());
        lessonDto.setTitle(lesson.getTitle());
        lessonDto.setTasks(lesson.getTasks().stream()
                .map(taskMapper::toDto)
                .toList());
        return lessonDto;
    }

    @Override
    public Lesson toModel(LessonRequestDto lessonRequestDto) {
        Lesson lesson = new Lesson();
        updateModelFromDto(lesson, lessonRequestDto);
        return lesson;
    }

    @Override
    public void updateModelFromDto(Lesson lesson, LessonRequestDto lessonRequestDto) {
        lesson.setSubject(lessonRequestDto.getSubject());
        lesson.setGrade(lessonRequestDto.getGrade());
        lesson.setTitle(lessonRequestDto.getTitle());
        if (lessonRequestDto.getTasks() != null && !lessonRequestDto.getTasks().isEmpty()) {
            List<Task> newTasks = lessonRequestDto.getTasks().stream()
                    .map(taskDto -> {
                        Task task = taskMapper.toModel(taskDto);
                        task.setLesson(lesson);
                        return task;
                    })
                    .toList();
            lesson.getTasks().clear();
            lesson.getTasks().addAll(newTasks);
        }
    }
}
