package khpi.kvp.webstore_spring.services;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import khpi.kvp.webstore_spring.dto.ProductFiltersDTO;
import khpi.kvp.webstore_spring.models.Product;
import khpi.kvp.webstore_spring.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService{
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAll() { return productRepository.findAll(); }
    public void addProduct(Product product) { productRepository.save(product); }
    public void removeProductById(Long id) { productRepository.deleteById(id); }
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
    public void updateProduct(Product product) { productRepository.save(product); }

    public List<Product> getProductsByCategoryId(Integer id) {
        return productRepository.findAllByCategory_id(id);
    }
    public List<Product> filterProducts(ProductFiltersDTO filters) {
        return productRepository.findAll((Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (filters.getMinPrice() > 0) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("price"), filters.getMinPrice()));
            }

            if (filters.getMaxPrice() > 0) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("price"), filters.getMaxPrice()));
            }

            if (filters.getCategoryId() != 0) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("category").get("id"), filters.getCategoryId()));
            }

            return query.where(predicate).getRestriction();
        });
    }
}
