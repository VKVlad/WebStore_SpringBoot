package khpi.kvp.webstore_spring.repositories;

import khpi.kvp.webstore_spring.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
