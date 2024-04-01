package pai.final_project.dao;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import pai.final_project.entity.Student;

@Transactional
public interface StudentDao extends JpaRepository<Student, Integer> {
}
