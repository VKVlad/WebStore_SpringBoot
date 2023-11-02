package khpi.kvp.webstore_spring.repositories;

import khpi.kvp.webstore_spring.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
