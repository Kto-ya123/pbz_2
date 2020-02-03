package corseproject.domain;


import javax.persistence.*;

@Entity
@Table(name = "subdivision")
public class Subdivision {
    @Id
    private long id;

    private String name;

    private String fullName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
