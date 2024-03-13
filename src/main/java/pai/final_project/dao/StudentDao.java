package pai.final_project.dao;

import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import pai.final_project.entity.Student;

@Transactional
public interface StudentDao extends CrudRepository<Student, Integer> {
}
