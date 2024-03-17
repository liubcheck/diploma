package liubomyr.stepanenko.lessonservice.mapper.impl;

import java.util.Optional;
import liubomyr.stepanenko.lessonservice.dto.request.VariantRequestDto;
import liubomyr.stepanenko.lessonservice.dto.response.VariantDto;
import liubomyr.stepanenko.lessonservice.mapper.BasicMapper;
import liubomyr.stepanenko.lessonservice.model.Task;
import liubomyr.stepanenko.lessonservice.model.Variant;
import org.springframework.stereotype.Component;

@Component
public class VariantMapper implements BasicMapper<Variant, VariantRequestDto, VariantDto> {

    @Override
    public VariantDto toDto(Variant variant) {
        VariantDto variantDto =  new VariantDto();
        variantDto.setId(variant.getId());
        variantDto.setValue(variant.getValue());
        variantDto.setIsRight(variant.getIsRight());
        return variantDto;
    }

    @Override
    public Variant toModel(VariantRequestDto variantRequestDto) {
        Variant variant = new Variant();
        variant.setId(variantRequestDto.getId());
        updateModelFromDto(variant, variantRequestDto);
        return variant;
    }

    @Override
    public void updateModelFromDto(Variant variant, VariantRequestDto variantRequestDto) {
        variant.setTask(getTaskFromId(variantRequestDto.getTaskId()));
        variant.setValue(variantRequestDto.getValue());
        variant.setIsRight(variantRequestDto.getIsRight());
    }

    private Task getTaskFromId(Long id) {
        return Optional.ofNullable(id)
                .map(Task::new)
                .orElse(null);
    }
}
