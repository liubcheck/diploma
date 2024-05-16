package liubomyr.stepanenko.lessonservice.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import liubomyr.stepanenko.lessonservice.dto.request.LessonRequestDto;
import liubomyr.stepanenko.lessonservice.dto.response.LessonDto;
import liubomyr.stepanenko.lessonservice.mapper.impl.LessonMapper;
import liubomyr.stepanenko.lessonservice.model.Lesson;
import liubomyr.stepanenko.lessonservice.model.Task;
import liubomyr.stepanenko.lessonservice.repository.LessonRepository;
import liubomyr.stepanenko.lessonservice.repository.TaskRepository;
import liubomyr.stepanenko.lessonservice.service.BasicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LessonService implements BasicService<LessonRequestDto, LessonDto> {
    private final LessonRepository lessonRepository;
    private final TaskRepository taskRepository;
    private final LessonMapper lessonMapper;

    @Override
    public LessonDto save(LessonRequestDto lessonRequestDto) {
        if (lessonRequestDto.getTasks() != null && lessonRequestDto.getTasks().size() != 10) {
            throw new IllegalArgumentException("Each lesson must have exactly 10 tasks.");
        }
        Lesson lesson = lessonMapper.toModel(lessonRequestDto);
        return lessonMapper.toDto(lessonRepository.save(lesson));
    }

    @Override
    public LessonDto getById(Long id) {
        Lesson lesson = findLessonById(id);
        return lessonMapper.toDto(lesson);
    }

    @Override
    public List<LessonDto> getAll() {
        return lessonRepository.findAll().stream()
                .map(lessonMapper::toDto)
                .toList();
    }

    @Override
    public LessonDto update(Long id, LessonRequestDto lessonRequestDto) {
        if (lessonRequestDto.getTasks() != null && lessonRequestDto.getTasks().size() != 10) {
            throw new IllegalArgumentException("Each lesson must have exactly 10 tasks.");
        }
        Lesson lessonForUpdate = findLessonById(id);
        lessonMapper.updateModelFromDto(lessonForUpdate, lessonRequestDto);
        return lessonMapper.toDto(lessonRepository.save(lessonForUpdate));
    }

    @Override
    public Long deleteById(Long id) {
        Lesson lesson = findLessonById(id);
        for (Task task : lesson.getTasks()) {
            task.setLesson(null);
            taskRepository.save(task);
        }
        lessonRepository.deleteById(id);
        return id;
    }

    public List<LessonDto> getAllByGrade(Integer grade) {
        return lessonRepository.findAllByGrade(grade).stream()
                .map(lessonMapper::toDto)
                .toList();
    }

    public List<LessonDto> getAllByGradeAndSubject(Integer grade, String subject) {
        return lessonRepository.findAllByGradeAndSubject(grade, subject).stream()
                .map(lessonMapper::toDto)
                .toList();
    }

    private Lesson findLessonById(Long id) {
        return lessonRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Lesson not found with id = " + id)
        );
    }
}
