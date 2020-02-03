package corseproject.repos;

import corseproject.domain.Subdivision;
import org.springframework.data.repository.CrudRepository;

public interface SubdivisionRepository extends CrudRepository<Subdivision, Long> {
    Subdivision findById(long id);
}