package pai.final_project.dao;

import org.springframework.data.repository.CrudRepository;
import pai.final_project.entity.Grade;

import java.util.List;

public interface GradeDao extends CrudRepository<Grade, Integer> {
    public List<Grade> findAllByStudentLoginAndSubjectName(String login, String subjectName);
}
