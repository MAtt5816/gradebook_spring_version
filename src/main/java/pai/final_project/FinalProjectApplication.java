package pai.final_project;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import pai.final_project.dao.*;
import pai.final_project.entity.*;
import pai.final_project.enums.UserRoles;

@SpringBootApplication
public class FinalProjectApplication {
	@Autowired
	private UserDao userDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private SubjectDao subjectDao;
	@Autowired
	private GradeDao gradeDao;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(FinalProjectApplication.class, args);
	}

	@PostConstruct
	public void init() {
		User user = userDao.findByLogin("admin");
		if (user == null){
			Student jan = new Student("Jan", "Kowalski", "jkowalski", passwordEncoder.encode("jkowalski"), "2", "2023/2024");
			Student anna = new Student("Anna", "Nowak", "anowak", passwordEncoder.encode("anowak"), "1", "2023/2024");
			Teacher nowak = new Teacher("Piotr", "Nowak", "pnowak", passwordEncoder.encode("pnowak"));
			Subject matematyka = new Subject("Matematyka");
			Subject polski = new Subject("J. polski");
			Subject angielski = new Subject("J. angielski");
			Subject historia = new Subject("Historia");


			userDao.save(new User("admin", "admin", "admin", passwordEncoder.encode("admin"), UserRoles.ADMIN));
			studentDao.save(jan);
			studentDao.save(anna);
			teacherDao.save(nowak);
			subjectDao.save(matematyka);
			subjectDao.save(polski);
			subjectDao.save(angielski);
			subjectDao.save(historia);
			gradeDao.save(new Grade(5., "", matematyka, jan, nowak));
			gradeDao.save(new Grade(4., "", polski, anna, nowak));
			gradeDao.save(new Grade(5., "", polski, anna, nowak));
			gradeDao.save(new Grade(3.5, "spr.", angielski, jan, nowak));
		}
	}
}
