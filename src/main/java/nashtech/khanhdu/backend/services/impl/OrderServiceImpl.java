package nashtech.khanhdu.backend.services.impl;

import nashtech.khanhdu.backend.dto.OrderDto;
import nashtech.khanhdu.backend.entities.Order;
import nashtech.khanhdu.backend.exceptions.ProductNotFoundException;
import nashtech.khanhdu.backend.exceptions.UserExistException;
import nashtech.khanhdu.backend.repositories.OrderRepository;
import nashtech.khanhdu.backend.repositories.ProductRepository;
import nashtech.khanhdu.backend.repositories.UserRepository;
import nashtech.khanhdu.backend.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;

        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<Order> createOrUpdateOrder(OrderDto dto) {
        Order order = orderRepository.findByUserNameAndProductName(dto.userId(), dto.productId());
        if (order == null) {
            if (dto.quantity() <= 0) {
                return ResponseEntity.ok(null);
            }
            order = new Order();
            order.setId(0L);
            order.setUserOrder(userRepository
                    .findById(dto.userId())
                    .orElseThrow(()-> new UserExistException("User not found")));
            order.setProductOrder(productRepository
                    .findById(dto.productId())
                    .orElseThrow(()-> new ProductNotFoundException("Product not found")));
        }
        if (dto.quantity() <= 0) {
            order.getUserOrder().getOrders().remove(order);
            order.getProductOrder().getOrders().remove(order);
            orderRepository.delete(order);
            return ResponseEntity.ok(null);
        }
        order.setQuantity(dto.quantity());
        orderRepository.save(order);
        System.out.println(order);
        return ResponseEntity.ok(order);
    }

    @Override
    public ResponseEntity<String> finishOrder(Long userId) {
        return null;
    }

    @Override
    public ResponseEntity<Order> deleteOrder(Order order) {
        orderRepository.delete(order);
        return ResponseEntity.ok(order);
    }

    @Override
    public List<Order> findAllByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
