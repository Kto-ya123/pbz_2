package corseproject.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@Table
public class Accident {
    private static final String FORMAT = "YYYY/MM/DD hh:mm";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @DateTimeFormat(pattern = "yyyy/MM/dd hh:mm")
    private Date dateOfAccident;

    @Column
    private Double costOfDamage;

    @OneToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.REMOVE})
    private Contract contract;

    @OneToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.REMOVE})
    private Car car;

    @OneToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.REMOVE})
    private Client client;
}
