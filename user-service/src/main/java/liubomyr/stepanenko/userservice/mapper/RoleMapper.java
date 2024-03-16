package liubomyr.stepanenko.userservice.mapper;

import liubomyr.stepanenko.userservice.dto.response.RoleDto;
import liubomyr.stepanenko.userservice.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public RoleDto toDto(Role role) {
        return new RoleDto(role.getName().name());
    }
}
