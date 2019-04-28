package corseproject.repos;

import corseproject.domain.Sex;
import corseproject.domain.TShirt;
import corseproject.domain.Topic;
import corseproject.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TShirtRepository extends CrudRepository<TShirt, Long> {
    List<TShirt> findByAuthor(User user);
    TShirt findById(long id);
    List<TShirt> findBySexAndTopic(Sex sex, Topic topic);
    List<TShirt> findBySex(Sex sex);
    List<TShirt> findByTopic(Topic topic);
}
