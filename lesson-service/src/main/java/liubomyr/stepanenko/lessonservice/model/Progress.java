package liubomyr.stepanenko.lessonservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Table(name = "progress")
@Data
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(table = "users", name = "user_email", nullable = false)
    private String userEmail;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;
    @Column(name = "is_completed", nullable = false)
    private Boolean isCompleted;
    @Column(name = "passing_date", nullable = false)
    private LocalDateTime passingDate;
    @Column(nullable = false)
    private Integer score;
}
