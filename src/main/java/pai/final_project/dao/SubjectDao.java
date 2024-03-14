package pai.final_project.dao;

import org.springframework.data.repository.CrudRepository;
import pai.final_project.entity.Subject;

public interface SubjectDao extends CrudRepository<Subject, Integer> {
}
