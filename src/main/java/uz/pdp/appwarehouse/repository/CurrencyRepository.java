package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.payload.Result;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    Result deleteCurrencyService(Integer id);
}
