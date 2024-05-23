package nashtech.khanhdu.backend.services;

import nashtech.khanhdu.backend.dto.OrderDto;
import nashtech.khanhdu.backend.entities.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface OrderService {
    ResponseEntity<Order> createOrUpdateOrder(OrderDto dto);

    ResponseEntity<String> finishOrder(Long userId);

    ResponseEntity<Order> deleteOrder(Order order);
}
