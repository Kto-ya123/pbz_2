package corseproject.repos;

import corseproject.domain.Flat;
import corseproject.domain.Subdivision;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FlatRepository extends CrudRepository<Flat, Long> {
    List<Flat> findBySubdivision (Subdivision subdivision);
    Flat findById(long id);
}
