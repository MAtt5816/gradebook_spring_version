package pai.final_project.dao;

import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import pai.final_project.entity.Teacher;

@Transactional
public interface TeacherDao extends CrudRepository<Teacher, Integer> {
}
