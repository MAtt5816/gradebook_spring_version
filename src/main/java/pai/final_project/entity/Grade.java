package pai.final_project.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Grades")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @DecimalMin("2.0")
    @DecimalMax("5.0")
    private Double mark;

    @Nullable
    private String comment;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Teacher teacher;

    public Grade(Double mark, String comment, Subject subject, Student student, Teacher teacher){
        this.mark = mark;
        this.comment = comment;
        this.subject = subject;
        this.student = student;
        this.teacher = teacher;
    }
}
