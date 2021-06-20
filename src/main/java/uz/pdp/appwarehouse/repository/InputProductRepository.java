package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.appwarehouse.entity.InputProduct;

@Repository
public interface InputProductRepository extends JpaRepository<InputProduct, Integer> {


}
