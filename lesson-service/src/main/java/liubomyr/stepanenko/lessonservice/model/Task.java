package liubomyr.stepanenko.lessonservice.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String question;
    @Column(name = "task_type", nullable = false)
    private String taskType;
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<Variant> variants = new ArrayList<>();
    private String rightAnswer;

    public Task(Long id) {
        this.id = id;
    }
}
