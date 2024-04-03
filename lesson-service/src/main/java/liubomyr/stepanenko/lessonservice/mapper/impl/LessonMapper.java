package liubomyr.stepanenko.lessonservice.mapper.impl;

import java.util.List;
import java.util.Optional;
import liubomyr.stepanenko.lessonservice.dto.request.LessonRequestDto;
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
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public LessonDto toDto(Lesson lesson) {
        LessonDto lessonDto = new LessonDto();
        lessonDto.setSubject(lessonDto.getSubject());
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
        if (lessonRequestDto.getTaskIds() != null) {
            List<Task> tasks = lessonRequestDto.getTaskIds().stream()
                    .map(taskRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();
            tasks.forEach(task -> task.setLesson(lesson));
            lesson.getTasks().clear();
            lesson.getTasks().addAll(tasks);
        }
    }
}
