package liubomyr.stepanenko.lessonservice.controller;

import jakarta.validation.Valid;
import java.util.List;
import liubomyr.stepanenko.lessonservice.dto.request.VariantRequestDto;
import liubomyr.stepanenko.lessonservice.dto.response.VariantDto;
import liubomyr.stepanenko.lessonservice.service.impl.VariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/variants")
@RequiredArgsConstructor
public class VariantController {
    private final VariantService variantService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public VariantDto save(@RequestBody @Valid VariantRequestDto requestDto) {
        return variantService.save(requestDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public VariantDto getById(@PathVariable Long id) {
        return variantService.getById(id);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public List<VariantDto> getAll() {
        return variantService.getAll();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public VariantDto update(@PathVariable Long id,
                          @RequestBody @Valid VariantRequestDto requestDto) {
        return variantService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Long deleteById(@PathVariable Long id) {
        variantService.deleteById(id);
        return id;
    }
}
