package corseproject.repos;

import corseproject.domain.Message;
import corseproject.domain.TShirt;
import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TShirtRepository extends CrudRepository<Message, Long> {
    //List<TShirt> findByAuthor(User user);
}
