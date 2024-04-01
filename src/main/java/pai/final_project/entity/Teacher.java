package pai.final_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pai.final_project.enums.UserRoles;

import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Teachers")
@PrimaryKeyJoinColumn(name = "users_id")
public class Teacher extends User{
    @OneToMany(cascade = CascadeType.ALL)
    private List<Grade> gradeList;

    public Teacher(String name, String surname, String login, String password) {
        super(name, surname, login, password, UserRoles.TEACHER);
        this.gradeList = Collections.emptyList();
    }

    public Teacher(User user){
        super(user.name, user.surname, user.login, user.password, UserRoles.TEACHER);
        this.gradeList = Collections.emptyList();
    }
}
