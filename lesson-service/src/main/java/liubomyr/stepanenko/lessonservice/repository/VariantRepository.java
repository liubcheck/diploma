package liubomyr.stepanenko.lessonservice.repository;

import liubomyr.stepanenko.lessonservice.model.Variant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {
}
