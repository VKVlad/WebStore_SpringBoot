package khpi.kvp.webstore_spring.repositories;

import khpi.kvp.webstore_spring.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}


