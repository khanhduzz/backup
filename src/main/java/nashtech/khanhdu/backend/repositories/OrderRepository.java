package nashtech.khanhdu.backend.repositories;

import nashtech.khanhdu.backend.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
