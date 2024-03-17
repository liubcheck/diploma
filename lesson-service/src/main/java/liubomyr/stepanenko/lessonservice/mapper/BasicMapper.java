package liubomyr.stepanenko.lessonservice.mapper;

public interface BasicMapper<Entity, RequestDto, ResponseDto> {
    ResponseDto toDto(Entity entity);

    Entity toModel(RequestDto requestDto);

    void updateModelFromDto(Entity entity, RequestDto requestDto);
}
