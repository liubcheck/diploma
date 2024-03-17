package liubomyr.stepanenko.lessonservice.mapper.impl;

import jakarta.persistence.EntityNotFoundException;
import liubomyr.stepanenko.lessonservice.dto.request.ProgressRequestDto;
import liubomyr.stepanenko.lessonservice.dto.response.ProgressDto;
import liubomyr.stepanenko.lessonservice.mapper.BasicMapper;
import liubomyr.stepanenko.lessonservice.model.Progress;
import liubomyr.stepanenko.lessonservice.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProgressMapper implements BasicMapper<Progress, ProgressRequestDto, ProgressDto> {
    private final LessonRepository lessonRepository;

    @Override
    public ProgressDto toDto(Progress progress) {
        ProgressDto dto = new ProgressDto();
        dto.setId(progress.getId());
        dto.setUserEmail(progress.getUserEmail());
        dto.setScore(progress.getScore());
        dto.setIsCompleted(progress.getIsCompleted());
        return dto;
    }

    @Override
    public Progress toModel(ProgressRequestDto requestDto) {
        Progress progress = new Progress();
        updateModelFromDto(progress, requestDto);
        return progress;
    }

    @Override
    public void updateModelFromDto(Progress progress, ProgressRequestDto requestDto) {
        progress.setLesson(lessonRepository.findById(requestDto.getLessonId()).orElseThrow(
                () -> new EntityNotFoundException("No lesson found with id = "
                        + requestDto.getLessonId())
        ));
        progress.setScore(requestDto.getScore());
        progress.setIsCompleted(requestDto.getScore() >= 8);
    }
}
