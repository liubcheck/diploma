package liubomyr.stepanenko.lessonservice.repository;

import java.util.List;
import liubomyr.stepanenko.lessonservice.model.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {
    List<Progress> findAllByUserEmail(String email);

    @Query("SELECT SUM(p.score) FROM Progress p WHERE p.userEmail = :email")
    Integer findTotalUserScore(String email);
}
