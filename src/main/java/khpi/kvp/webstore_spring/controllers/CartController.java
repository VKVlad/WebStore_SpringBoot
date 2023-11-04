package khpi.kvp.webstore_spring.controllers;

import jakarta.servlet.http.HttpSession;
import khpi.kvp.webstore_spring.models.Category;
import khpi.kvp.webstore_spring.models.Product;
import khpi.kvp.webstore_spring.services.CartService;
import khpi.kvp.webstore_spring.services.ProductService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping("/addToCart/{id}")
    public String addToCart(@PathVariable Long id, @RequestParam int quantity, HttpSession session) {
        cartService.addItem(session, id, quantity);
        return "redirect:/shop";
    }

    @GetMapping("/cart")
    public String cartGet(Model model, HttpSession session) {
        model.addAttribute("cartCount", cartService.getCartSize(session));
        model.addAttribute("total", cartService.calculateTotal(session));
        model.addAttribute("cart", cartService.getCart(session));
        return "cart";
    }

    @GetMapping("/cart/removeItem/{id}")
    public String removeItem(@PathVariable Long id, HttpSession session) {
        cartService.removeItem(session, id);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model, HttpSession session) {
        model.addAttribute("total", cartService.calculateTotal(session));
        return "checkout";
    }
    @PostMapping("/cart/updateQuantity/{id}")
    public String updateQuantity(@PathVariable Long id, @RequestParam int newQuantity, HttpSession session) {
        cartService.updateQuantity(session, id, newQuantity);
        return "redirect:/cart";
    }

}

