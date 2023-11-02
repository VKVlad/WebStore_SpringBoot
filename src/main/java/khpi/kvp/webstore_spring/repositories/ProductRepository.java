package khpi.kvp.webstore_spring.repositories;

import khpi.kvp.webstore_spring.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
