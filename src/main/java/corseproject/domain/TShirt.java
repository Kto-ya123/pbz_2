package corseproject.domain;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tshirt")
@Indexed
public class TShirt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Field(termVector = TermVector.YES)
    @NotEmpty
    private String name;

    @Field(termVector = TermVector.YES)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @NotEmpty
    private String urlShirt;

    private Sex sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @Field(termVector = TermVector.YES)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "tag_id")
    List<Tag> tags;



    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrlShirt() {
        return urlShirt;
    }

    public void setUrlShirt(String urlShirt) {
        this.urlShirt = urlShirt;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
