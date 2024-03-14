package pai.final_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Teachers")
@PrimaryKeyJoinColumn(name = "users_id")
public class Teacher extends User{
    @OneToMany(cascade = CascadeType.ALL)
    private List<Grade> gradeList;
}
