package liubomyr.stepanenko.lessonservice.repository;

import java.util.List;
import liubomyr.stepanenko.lessonservice.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findAllByGrade(Integer grade);

    List<Lesson> findAllByGradeAndSubject(Integer grade, String subject);
}
