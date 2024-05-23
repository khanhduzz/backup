package nashtech.khanhdu.backend.controllers;

import nashtech.khanhdu.backend.dto.OrderDto;
import nashtech.khanhdu.backend.entities.Order;
import nashtech.khanhdu.backend.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping()
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Order> createOrUpdateOrder(@RequestBody OrderDto dto) {
        return orderService.createOrUpdateOrder(dto);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('USER')")
    public List<Order> findAllOrderById(@PathVariable("userId") Long userId) {
        return orderService.findAllByUserId(userId);
    }
}
