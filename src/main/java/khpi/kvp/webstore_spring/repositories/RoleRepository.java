package khpi.kvp.webstore_spring.repositories;

import khpi.kvp.webstore_spring.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
