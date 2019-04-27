package corseproject.repos;

import corseproject.domain.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TagRepository extends CrudRepository<Tag, Long> {
    Tag findByTagName (String tagName);
}