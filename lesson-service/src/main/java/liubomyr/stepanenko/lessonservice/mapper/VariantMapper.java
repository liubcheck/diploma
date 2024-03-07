package liubomyr.stepanenko.lessonservice.mapper;

import liubomyr.stepanenko.lessonservice.config.MapperConfig;
import liubomyr.stepanenko.lessonservice.dto.request.VariantRequestDto;
import liubomyr.stepanenko.lessonservice.dto.response.VariantDto;
import liubomyr.stepanenko.lessonservice.model.Variant;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface VariantMapper {
    VariantDto toDto(Variant variant);

    Variant toModel(VariantRequestDto variantRequestDto);

    void updateModelFromDto(@MappingTarget Variant variant, VariantRequestDto variantRequestDto);
}
