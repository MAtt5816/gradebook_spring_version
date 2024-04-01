package pai.final_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pai.final_project.enums.UserRoles;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Students")
@PrimaryKeyJoinColumn(name = "users_id")
public class Student extends User{
    private String studentGroup;

    @Pattern(regexp = "^$|^\\d{4}/\\d{4}$")
    private String schoolYear;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Grade> gradeList;

    public Student(String name, String surname, String login,
                   String password, String studentGroup, String schoolYear) {
        super(name, surname, login, password, UserRoles.STUDENT);
        this.studentGroup = studentGroup;
        this.schoolYear = schoolYear;
        this.gradeList = Collections.emptyList();
    }

    public Student(User user){
        super(user.name, user.surname, user.login, user.password, UserRoles.STUDENT);
        this.studentGroup = "";
        this.schoolYear = "";
        this.gradeList = Collections.emptyList();
    }
}
