package liubomyr.stepanenko.lessonservice.repository;

import java.util.List;
import liubomyr.stepanenko.lessonservice.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByLessonId(Long lessonId);
}
