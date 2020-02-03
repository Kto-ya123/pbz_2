package corseproject.domain;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "broadcast")
public class Broadcast {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date date;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "technics_id")
    private Technics technics;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subdivision_id")
    private Subdivision fromSubvision;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee toEmployee;

    public Subdivision getFromSubvision() {
        return fromSubvision;
    }

    public void setFromSubvision(Subdivision fromSubvision) {
        this.fromSubvision = fromSubvision;
    }

    public Employee getToEmployee() {
        return toEmployee;
    }

    public void setToEmployee(Employee toEmployee) {
        this.toEmployee = toEmployee;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Technics getTechnics() {
        return technics;
    }

    public void setTechnics(Technics technics) {
        this.technics = technics;
    }
}
