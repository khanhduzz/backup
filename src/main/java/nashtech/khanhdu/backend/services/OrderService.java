package nashtech.khanhdu.backend.services;

import nashtech.khanhdu.backend.dto.OrderDto;
import nashtech.khanhdu.backend.entities.Order;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    ResponseEntity<Order> createOrUpdateOrder(OrderDto dto);

    ResponseEntity<String> finishOrder(Long userId);

    ResponseEntity<Order> deleteOrder(Order order);

    List<Order> findAllByUserId(Long userId);
}
