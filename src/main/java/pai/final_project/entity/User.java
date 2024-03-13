package pai.final_project.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import pai.final_project.enums.UserRoles;

@Entity
@Getter
@Setter
@Table(name = "Users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    @NotNull
    @NotEmpty
    protected String name;

    @NotNull
    @NotEmpty
    protected String surname;

    @NotNull
    @NotEmpty
    @Column(unique = true)
    protected String login;

    @NotNull
    @NotEmpty
    @Size(min = 8)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$")
    protected String password;

    @NotNull
    private UserRoles role;

    public User() {
    }

    public User(String name, String surname, String login,
                String password, UserRoles role) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.role = role;
    }
}
