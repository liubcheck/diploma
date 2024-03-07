package liubomyr.stepanenko.lessonservice.controller;

import jakarta.validation.Valid;
import java.util.List;
import liubomyr.stepanenko.lessonservice.service.BasicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
public abstract class AbstractController<RequestDto, ResponseDto> {
    private final BasicService<RequestDto, ResponseDto> service;

    @PostMapping
    public ResponseDto save(@RequestBody @Valid RequestDto requestDto) {
        return service.save(requestDto);
    }

    @GetMapping("/{id}")
    public ResponseDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<ResponseDto> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public ResponseDto update(@PathVariable Long id, @RequestBody @Valid RequestDto requestDto) {
        return service.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public Long deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return id;
    }
}
