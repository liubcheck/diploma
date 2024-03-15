package liubomyr.stepanenko.lessonservice.controller;

import liubomyr.stepanenko.lessonservice.dto.request.LessonRequestDto;
import liubomyr.stepanenko.lessonservice.dto.response.LessonDto;
import liubomyr.stepanenko.lessonservice.service.BasicService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lessons")
public class LessonController extends AbstractController<LessonRequestDto, LessonDto> {
    public LessonController(BasicService<LessonRequestDto, LessonDto> service) {
        super(service);
    }
}
