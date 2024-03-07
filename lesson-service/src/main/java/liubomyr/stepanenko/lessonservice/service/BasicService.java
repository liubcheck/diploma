package liubomyr.stepanenko.lessonservice.service;

import java.util.List;

public interface BasicService<RequestDto, ResponseDto> {
    ResponseDto save(RequestDto requestDto);

    ResponseDto getById(Long id);

    List<ResponseDto> getAll();

    ResponseDto update(Long id, RequestDto requestDto);

    Long deleteById(Long id);
}
