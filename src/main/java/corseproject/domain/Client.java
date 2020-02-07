package corseproject.domain;

import javax.persistence.*;

@Entity
@Table
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String surname;

    @Column
    private String name;

    @Column
    private String patronymic;

    @Column
    private String passport;

    @Column
    private Integer experience;

    @Column
    private String telephoneNumber;

    @Column
    private String address;

}
