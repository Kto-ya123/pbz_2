package corseproject.repos;

import corseproject.domain.Employee;
import corseproject.domain.Flat;
import corseproject.domain.Subdivision;
import corseproject.domain.Technics;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TechnicsRepository extends CrudRepository<Technics, Long> {
    List<Technics> findByFlat (Flat flat);
    List<Technics> findByEmployee (Employee employee);
    Technics findByIdNumber(long idNumber);
}

