package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appwarehouse.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
}
