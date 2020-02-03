package corseproject.repos;

import corseproject.domain.Broadcast;
import corseproject.domain.Employee;
import corseproject.domain.Position;
import corseproject.domain.Subdivision;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BroadcastRepository extends CrudRepository<Broadcast, Long> {
}
