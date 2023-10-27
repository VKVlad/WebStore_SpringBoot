package khpi.kvp.webstore_spring.repositories;

import khpi.kvp.webstore_spring.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository{
    List<User> findAll();
    User findUserById(Long id);
    User findUserByUsername(String username);
    void addUser(User user);
   void updateUser(User user);
    void deleteUser(Long id);
}