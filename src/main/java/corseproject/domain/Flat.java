package corseproject.domain;


import javax.persistence.*;

@Entity
@Table(name = "flat")
public class Flat {
    @Id
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subdivision_id")
    private Subdivision subdivision;

    private int square;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Subdivision getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(Subdivision subdivision) {
        this.subdivision = subdivision;
    }

    public int getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
    }
}
