package liubomyr.stepanenko.lessonservice.service.impl;

import java.util.List;
import liubomyr.stepanenko.lessonservice.dto.request.ProgressRequestDto;
import liubomyr.stepanenko.lessonservice.dto.response.ProgressDto;
import liubomyr.stepanenko.lessonservice.mapper.impl.ProgressMapper;
import liubomyr.stepanenko.lessonservice.model.Progress;
import liubomyr.stepanenko.lessonservice.repository.ProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProgressService {
    private final ProgressRepository progressRepository;
    private final ProgressMapper progressMapper;

    public ProgressDto save(String email, ProgressRequestDto requestDto) {
        Progress progress = progressMapper.toModel(requestDto);
        progress.setUserEmail(email);
        return progressMapper.toDto(progressRepository.save(progress));
    }

    public List<ProgressDto> getAllByUserEmail(String email) {
        return progressRepository.findAllByUserEmail(email).stream()
                .map(progressMapper::toDto)
                .toList();
    }

    public Integer getUserTotalScore(String email) {
        return progressRepository.findTotalUserScore(email);
    }
}
