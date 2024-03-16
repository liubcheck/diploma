package liubomyr.stepanenko.userservice.repository;

import java.util.Optional;
import liubomyr.stepanenko.userservice.model.Role;
import liubomyr.stepanenko.userservice.model.type.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
