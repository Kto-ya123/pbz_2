package corseproject.repos;

import corseproject.domain.TShirt;
import corseproject.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TShirtRepository extends CrudRepository<TShirt, Long> {
    List<TShirt> findByAuthor(User user);
    TShirt findById(long id);
}
