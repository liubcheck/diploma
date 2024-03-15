package liubomyr.stepanenko.lessonservice.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import liubomyr.stepanenko.lessonservice.dto.request.VariantRequestDto;
import liubomyr.stepanenko.lessonservice.dto.response.VariantDto;
import liubomyr.stepanenko.lessonservice.mapper.VariantMapper;
import liubomyr.stepanenko.lessonservice.model.Variant;
import liubomyr.stepanenko.lessonservice.repository.VariantRepository;
import liubomyr.stepanenko.lessonservice.service.BasicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("variantService")
@RequiredArgsConstructor
public class VariantService implements BasicService<VariantRequestDto, VariantDto> {
    private final VariantRepository variantRepository;
    private final VariantMapper variantMapper;

    @Override
    public VariantDto save(VariantRequestDto variantRequestDto) {
        Variant variant = variantMapper.toModel(variantRequestDto);
        return variantMapper.toDto(variantRepository.save(variant));
    }

    @Override
    public VariantDto getById(Long id) {
        return variantMapper.toDto(variantRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Variant not found with id = " + id)
        ));
    }

    @Override
    public List<VariantDto> getAll() {
        return variantRepository.findAll().stream()
                .map(variantMapper::toDto)
                .toList();
    }

    @Override
    public VariantDto update(Long id, VariantRequestDto variantRequestDto) {
        Variant variantForUpdate = variantRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("No variant is found by id = " + id)
        );
        variantMapper.updateModelFromDto(variantForUpdate, variantRequestDto);
        return variantMapper.toDto(variantRepository.save(variantForUpdate));
    }

    @Override
    public Long deleteById(Long id) {
        variantRepository.deleteById(id);
        return id;
    }
}
