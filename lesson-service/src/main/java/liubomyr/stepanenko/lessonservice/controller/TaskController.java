package liubomyr.stepanenko.lessonservice.controller;

import liubomyr.stepanenko.lessonservice.dto.request.TaskRequestDto;
import liubomyr.stepanenko.lessonservice.dto.response.TaskDto;
import liubomyr.stepanenko.lessonservice.service.BasicService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class TaskController extends AbstractController<TaskRequestDto, TaskDto> {
    public TaskController(BasicService<TaskRequestDto, TaskDto> service) {
        super(service);
    }
}
