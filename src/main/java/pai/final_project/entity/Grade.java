package pai.final_project.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Grades")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @NotEmpty
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
}
