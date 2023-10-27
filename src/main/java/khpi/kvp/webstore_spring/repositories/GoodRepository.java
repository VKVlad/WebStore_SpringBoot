package khpi.kvp.webstore_spring.repositories;

import khpi.kvp.webstore_spring.models.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodRepository extends JpaRepository<Good, Long> {
    List<Good> findByNazva(String nazva);
}
