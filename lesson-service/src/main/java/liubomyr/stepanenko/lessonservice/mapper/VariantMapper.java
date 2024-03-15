package liubomyr.stepanenko.lessonservice.mapper;

import java.util.Optional;
import liubomyr.stepanenko.lessonservice.dto.request.VariantRequestDto;
import liubomyr.stepanenko.lessonservice.dto.response.VariantDto;
import liubomyr.stepanenko.lessonservice.model.Task;
import liubomyr.stepanenko.lessonservice.model.Variant;
import org.springframework.stereotype.Component;

@Component
public class VariantMapper {

    public VariantDto toDto(Variant variant) {
        VariantDto variantDto =  new VariantDto();
        variantDto.setId(variant.getId());
        variantDto.setValue(variant.getValue());
        variantDto.setIsRight(variant.getIsRight());
        return variantDto;
    }

    public Variant toModel(VariantRequestDto variantRequestDto) {
        Variant variant = new Variant();
        variant.setId(variantRequestDto.getId());
        updateModelFromDto(variant, variantRequestDto);
        return variant;
    }

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
