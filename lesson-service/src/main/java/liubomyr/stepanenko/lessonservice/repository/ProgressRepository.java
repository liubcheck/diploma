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

    @Query("SELECT AVG(p.score) FROM Progress p WHERE p.userEmail = :email")
    Double findAverageScoreByUserEmail(String email);

    @Query("SELECT AVG(c.attempts) FROM (" +
            "SELECT COUNT(p) as attempts FROM Progress p " +
            "WHERE p.userEmail = :userEmail GROUP BY p.lesson) c")
    Double findAverageLessonAttemptsNumber(String userEmail);

    @Query("SELECT MAX(p.score) FROM Progress p WHERE p.userEmail = :email")
    Integer findBestScoreByUserEmail(String email);

    @Query("SELECT MIN(p.score) FROM Progress p WHERE p.userEmail = :email")
    Integer findWorstScoreByUserEmail(String email);

    @Query(value = "SELECT date_trunc('day', p.passing_date) as test_day, COUNT(p) as test_count " +
            "FROM progress p " +
            "WHERE p.user_email = :email AND p.passing_date >= current_date - interval '7 days' " +
            "GROUP BY test_day " +
            "ORDER BY test_day ASC", nativeQuery = true)
    List<Object[]> countTestsLastWeekByDay(String email);

    @Query("SELECT p.userEmail, SUM(p.score) as totalScore " +
            "FROM Progress p " +
            "GROUP BY p.userEmail " +
            "ORDER BY totalScore DESC " +
            "LIMIT 10")
    List<Object[]> findTopTenUsernamesByScore();
}
