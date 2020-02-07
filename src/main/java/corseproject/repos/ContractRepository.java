package corseproject.repos;


import corseproject.domain.Car;
import corseproject.domain.Client;
import corseproject.domain.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {
    List<Contract> findContractByClient(Client client);
    List<Contract> findContractByCar(Car car);
    List<Contract> findContractByClientAndCar(Client client, Car car);
}
