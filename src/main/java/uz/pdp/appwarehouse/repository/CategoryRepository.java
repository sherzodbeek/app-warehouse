package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appwarehouse.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
