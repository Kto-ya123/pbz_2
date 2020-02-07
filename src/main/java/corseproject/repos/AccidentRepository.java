package corseproject.repos;


import corseproject.domain.Accident;
import corseproject.domain.Car;
import corseproject.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccidentRepository extends JpaRepository<Accident, Integer> {
    List<Accident> findAccidentByClient(Client client);
    List<Accident> findAccidentByCar(Car car);
    Accident findAccidentByClientAndCar(Client client, Car car);
}
