package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.appwarehouse.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select max(id) from users", nativeQuery = true)
    int getMaxId();
}
