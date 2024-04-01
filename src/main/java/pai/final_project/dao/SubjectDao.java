package pai.final_project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pai.final_project.entity.Subject;

public interface SubjectDao extends JpaRepository<Subject, Integer> {
}
