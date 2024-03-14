package pai.final_project.dao;

import org.springframework.data.repository.CrudRepository;
import pai.final_project.entity.Grade;

public interface GradeDao extends CrudRepository<Grade, Integer> {
}
