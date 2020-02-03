package corseproject.repos;

import corseproject.domain.Employee;
import corseproject.domain.Position;
import corseproject.domain.Subdivision;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findBySubdivision (Subdivision subdivision);
    Employee findBySubdivisionAndPosition(Subdivision subdivision, Position position);
    Employee findById(long id);
}
