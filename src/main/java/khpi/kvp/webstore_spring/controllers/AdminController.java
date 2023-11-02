package khpi.kvp.webstore_spring.controllers;

import khpi.kvp.webstore_spring.models.Category;
import khpi.kvp.webstore_spring.models.Product;
import khpi.kvp.webstore_spring.services.CategoryService;
import khpi.kvp.webstore_spring.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Slf4j
@Controller
public class AdminController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @GetMapping("/admin")
    public String adminHome() {
        return "adminHome";
    }
    @GetMapping("/admin/categories")
    public String getCategories(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        return "categories";
    }
    @GetMapping("/admin/categories/add")
    public String getCategoriesAdd(Model model) {
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }

    @PostMapping("/admin/categories/add")
    public String postCategoriesAdd(@ModelAttribute("category") Category category) {
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }
    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCategory(@PathVariable Integer id) {
        categoryService.removeCategoryById(id);
        return "redirect:/admin/categories";
    }
    @GetMapping("/admin/categories/update/{id}")
    public String updateCategory(@PathVariable Integer id, Model model) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if(category.isPresent()) {
            model.addAttribute("category", category);
            return "categoriesAdd";
        }else {
            return "404";
        }
    }
    //Products
    @GetMapping("/admin/products")
    public String getProducts(Model model) {
        model.addAttribute("products", productService.getAll());
        return "products";
    }
    @GetMapping("/admin/products/add")
    public String getProductsAdd(Model model) {
        model.addAttribute("productDTO", new Product());
        model.addAttribute("categories", categoryService.getAll());
        return "productsAdd";
    }
    @PostMapping("/admin/products/add")
    public String postProductsAdd(@ModelAttribute("category") Product product) {
        productService.addProduct(product);
        return "redirect:/admin/products";
    }
    @GetMapping("/admin/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.removeProductById(id);
        return "redirect:/admin/products";
    }
    @GetMapping("/admin/products/update/{id}")
    public String updateProduct(@PathVariable Long id, Model model) {
        Optional<Product> product = productService.getProductById(id);
        if(product.isPresent()) {
            model.addAttribute("product", product);
            return "productsAdd";
        }else {
            return "404";
        }
    }
}
