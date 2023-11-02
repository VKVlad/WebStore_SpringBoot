package khpi.kvp.webstore_spring.services;

import khpi.kvp.webstore_spring.models.Category;
import khpi.kvp.webstore_spring.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }
    public void removeCategoryById(Integer id) { categoryRepository.deleteById(id); }
    public Optional<Category> getCategoryById(Integer id) {
        return categoryRepository.findById(id);
    }
    public void updateCategory(Category category) { categoryRepository.save(category); }
}
