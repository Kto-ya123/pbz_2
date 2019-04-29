package corseproject.repos;

import corseproject.domain.Rating;
import corseproject.domain.TShirt;
import corseproject.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RatingRepository extends CrudRepository<Rating, Long> {
    List<Rating> findByTShirt(TShirt tShirt);
    List<Rating> findByAuthor(User user);
    Rating findByTShirtAndAuthor(TShirt tShirt, User user);
    void deleteByTShirt(TShirt tShirt);
    void deleteByAuthor(User user);
}
