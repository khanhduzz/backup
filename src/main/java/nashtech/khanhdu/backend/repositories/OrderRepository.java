package nashtech.khanhdu.backend.repositories;

import nashtech.khanhdu.backend.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o JOIN o.userOrder u JOIN o.productOrder p " +
            "WHERE u.id = :userId AND p.id = :productId")
    Order findByUserNameAndProductName(@Param("userId") Long userId, @Param("productId") Long productId);

    @Query("SELECT o FROM Order o WHERE o.userOrder.id = :userId")
    List<Order> findByUserId(@Param("userId") Long userId);
}
