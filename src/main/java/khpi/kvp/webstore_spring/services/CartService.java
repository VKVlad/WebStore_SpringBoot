package khpi.kvp.webstore_spring.services;

import jakarta.servlet.http.HttpSession;
import khpi.kvp.webstore_spring.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;
import java.util.Map;

@Service
@SessionScope
public class CartService {

    @Autowired
    private ProductService productService;

    public void addItem(HttpSession session, Long productId, int quantity) {
        Product product = productService.getProductById(productId).orElse(null);

        if (product != null) {
            Map<Product, Integer> cartItems = getCart(session);

            cartItems.put(product, cartItems.getOrDefault(product, 0) + quantity);
        }
    }
    public void updateQuantity(HttpSession session, Long productId, int newQuantity) {
        Map<Product, Integer> cartItems = getCart(session);
        Product product = productService.getProductById(productId).orElse(null);

        if (product != null && newQuantity > 0) {
            cartItems.put(product, newQuantity);
        }
    }

    public void removeItem(HttpSession session, Long productId) {
        Product product = productService.getProductById(productId).orElse(null);

        if (product != null) {
            Map<Product, Integer> cartItems = getCart(session);
            cartItems.remove(product);
            System.out.println("Removed");
        }
    }

    public Map<Product, Integer> getCart(HttpSession session) {
        Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");

        if (cart == null) {
            cart = new HashMap<>();
            session.setAttribute("cart", cart);
        }

        return cart;
    }

    public int getCartSize(HttpSession session) {
        Map<Product, Integer> cartItems = getCart(session);
        return cartItems.size();
    }

    public void clear(HttpSession session) {
        Map<Product, Integer> cartItems = getCart(session);
        cartItems.clear();
    }

    public double calculateTotal(HttpSession session) {
        Map<Product, Integer> cartItems = getCart(session);

        return cartItems.entrySet()
                .stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }
}
