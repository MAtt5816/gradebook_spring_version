package pai.final_project;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import pai.final_project.dao.UserDao;
import pai.final_project.entity.User;
import pai.final_project.enums.UserRoles;

@SpringBootApplication
public class FinalProjectApplication {
	@Autowired
	private UserDao dao;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(FinalProjectApplication.class, args);
	}

	@PostConstruct
	public void init() {
		User user = dao.findByLogin("admin");
		if (user == null){
			dao.save(new User("admin", "admin", "admin", passwordEncoder.encode("admin"), UserRoles.ADMIN));
		}
	}
}
