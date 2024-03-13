package pai.final_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Teachers")
@PrimaryKeyJoinColumn(name = "users_id")
public class Teacher extends User{
}
