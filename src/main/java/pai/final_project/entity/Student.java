package pai.final_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Students")
@PrimaryKeyJoinColumn(name = "users_id")
public class Student extends User{
    @NotNull
    @NotEmpty
    private String studentGroup;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^\\d{4}/\\d{4}$")
    private String schoolYear;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Grade> gradeList;
}
