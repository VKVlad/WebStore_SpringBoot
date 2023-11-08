package khpi.kvp.webstore_spring.controllers;

import jakarta.servlet.http.HttpSession;
import khpi.kvp.webstore_spring.dto.ProductFiltersDTO;
import khpi.kvp.webstore_spring.models.Product;
import khpi.kvp.webstore_spring.services.CartService;
import khpi.kvp.webstore_spring.services.CategoryService;
import khpi.kvp.webstore_spring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CartService cartService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        return "index";
    }
    @GetMapping("/shop")
    public String shop(Model model, HttpSession session) {
        model.addAttribute("filters", new ProductFiltersDTO());
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("products", productService.getAll());
        model.addAttribute("cartCount", cartService.getCartSize(session));
        return "shop";
    }
    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id).get();
        model.addAttribute("product", product);
        return "viewProduct";
    }
    @GetMapping("/shop/category/{id}")
    public String shopCategory(@PathVariable Integer id, Model model, HttpSession session) {
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("cartCount",  cartService.getCartSize(session));
        model.addAttribute("products", productService.getProductsByCategoryId(id));
        return "shop";
    }

    @GetMapping("/shop/filter")
    public String filterProducts(@ModelAttribute("filters") ProductFiltersDTO filters, Model model) {
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("products", productService.filterProducts(filters));
        return "shop";
    }
}
