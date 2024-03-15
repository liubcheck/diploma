package liubomyr.stepanenko.lessonservice.repository;

import liubomyr.stepanenko.lessonservice.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
