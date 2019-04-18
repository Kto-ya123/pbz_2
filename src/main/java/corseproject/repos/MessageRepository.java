package corseproject.repos;

import corseproject.domain.Message;
import corseproject.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface MessageRepository extends CrudRepository<Message, Integer> {
    List<Message> findByTag(String tag);
    List<Message> findByAuthor(User user);

}