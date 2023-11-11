package khpi.kvp.webstore_spring.controllers;

import khpi.kvp.webstore_spring.dto.ProductDTO;
import khpi.kvp.webstore_spring.models.Category;
import khpi.kvp.webstore_spring.models.Product;
import khpi.kvp.webstore_spring.repositories.OrderItemRepository;
import khpi.kvp.webstore_spring.services.CategoryService;
import khpi.kvp.webstore_spring.services.OrderService;
import khpi.kvp.webstore_spring.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Slf4j
@Controller
public class AdminController {
    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/images";
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    private OrderService orderService;
    @GetMapping("/admin")
    public String adminHome(Model model) {
        model.addAttribute("categories", categoryService.getAll());
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
        model.addAttribute("categories", categoryService.getAll());
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
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("products", productService.getAll());
        return "products";
    }
    @GetMapping("/admin/products/add")
    public String getProductsAdd(Model model) {
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories", categoryService.getAll());
        return "productsAdd";
    }
    @PostMapping("/admin/products/add")
    public String postProductsAdd(@ModelAttribute("productDTO") ProductDTO productDTO,
                                  @RequestParam("productImage") MultipartFile file,
                                  @RequestParam("imgName") String imgName) throws IOException {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
        product.setPrice(productDTO.getPrice());
        product.setPriceOpt(productDTO.getPriceOpt());
        product.setArticle(productDTO.getArticle());
        product.setDescription(productDTO.getDescription());
        String imgUUID;
        if(!file.isEmpty()){
            imgUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imgUUID);
            Files.write(fileNameAndPath, file.getBytes());
        } else {
            imgUUID = imgName;
        }
        product.setImageName(imgUUID);
        productService.addProduct(product);
        return "redirect:/admin/products";
    }
    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.removeProductById(id);
        return "redirect:/admin/products";
    }
    @GetMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id).get();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setPrice(product.getPrice());
        productDTO.setPriceOpt(product.getPriceOpt());
        productDTO.setArticle(product.getArticle());
        productDTO.setDescription(product.getDescription());
        productDTO.setImageName(product.getImageName());
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("productDTO", productDTO);
        return "productsAdd";
    }

    @GetMapping("/admin/orders")
    public String getOrders(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("orderItems", orderItemRepository.findAll());
        return "orders";
    }

    @GetMapping("/admin/orders/delete/{id}")
    public String deleteOrder(@PathVariable("id") Long orderId) {
        orderService.deleteOrder(orderId);
        return "redirect:/admin/orders";
    }
}
