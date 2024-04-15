package liubomyr.stepanenko.userservice.repository;

import java.util.Optional;
import liubomyr.stepanenko.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username = :data OR u.email = :data")
    Optional<User> findByUsernameOrEmail(String data);
}
