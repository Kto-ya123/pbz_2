package corseproject.domain;

import javax.persistence.*;


@Entity
@Table
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String model;

    @Column
    private String color;

    @Column
    private BodyType bodyType;

    @Column
    private Transmission transmission;

    @Column
    private String vehicleNumber;

    @Column
    private Boolean isFree;

    @Column
    private Double price;
}
