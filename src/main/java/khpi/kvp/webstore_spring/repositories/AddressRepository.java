package khpi.kvp.webstore_spring.repositories;

import khpi.kvp.webstore_spring.models.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressRepository {
    List<Address> findAll();
    Address findAddressById(Long id);
    Address findAddressByCity(String city);
    void addAddress(Address address);
    void updateAddress(Address address);
    void deleteAddress(Address address);
}