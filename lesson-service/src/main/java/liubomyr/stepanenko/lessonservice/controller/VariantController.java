package liubomyr.stepanenko.lessonservice.controller;

import liubomyr.stepanenko.lessonservice.dto.request.VariantRequestDto;
import liubomyr.stepanenko.lessonservice.dto.response.VariantDto;
import liubomyr.stepanenko.lessonservice.service.BasicService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/variants")
public class VariantController extends AbstractController<VariantRequestDto, VariantDto> {
    public VariantController(BasicService<VariantRequestDto, VariantDto> service) {
        super(service);
    }
}
