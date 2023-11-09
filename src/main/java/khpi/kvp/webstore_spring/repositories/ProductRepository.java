package khpi.kvp.webstore_spring.repositories;

import khpi.kvp.webstore_spring.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategory_id(Integer id);
    List<Product> findAll(Specification<Product> spec);

    @Query("SELECT MAX(p.id) FROM Product p")
    Long findMaxProductId();

    @Query("SELECT MIN(p.id) FROM Product p")
    Long findMinProductId();
}
