package corseproject.domain;

import javax.persistence.*;

@Entity
@Table(name = "tshirt")
public class TShirt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String urlShirt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

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
}
