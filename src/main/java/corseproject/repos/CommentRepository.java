package corseproject.repos;

import corseproject.domain.Comment;
import corseproject.domain.TShirt;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findByTShirt (TShirt tShirt);
}
