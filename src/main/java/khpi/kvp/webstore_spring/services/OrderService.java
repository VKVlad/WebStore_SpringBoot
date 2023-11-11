package khpi.kvp.webstore_spring.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import khpi.kvp.webstore_spring.models.Order;
import khpi.kvp.webstore_spring.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void deleteOrder(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        optionalOrder.ifPresent(order -> {
            entityManager.createQuery("DELETE FROM OrderItem oi WHERE oi.orders = :order")
                    .setParameter("order", order)
                    .executeUpdate();

            // Delete the order
            orderRepository.delete(order);
        });
    }
}
