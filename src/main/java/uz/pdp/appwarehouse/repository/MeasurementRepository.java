package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appwarehouse.entity.Measurement;
@Repository

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

    boolean existsByName(String name);

}
