package khpi.kvp.webstore_spring.services;

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

    public Page<Product> findProductsByCriteria(Pageable pageable, Integer priceLow, Integer priceHigh,
                                                List<String> categories, String search) {
        return productRepository.findAll(ArticleSpecification.filterBy(priceLow, priceHigh, categories, search), pageable);
    }
}
