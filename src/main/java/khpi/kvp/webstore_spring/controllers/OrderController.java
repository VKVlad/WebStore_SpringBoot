package khpi.kvp.webstore_spring.controllers;

import jakarta.servlet.http.HttpSession;
import khpi.kvp.webstore_spring.dto.OrderDTO;
import khpi.kvp.webstore_spring.models.Order;
import khpi.kvp.webstore_spring.models.OrderItem;
import khpi.kvp.webstore_spring.models.Product;
import khpi.kvp.webstore_spring.repositories.OrderItemRepository;
import khpi.kvp.webstore_spring.repositories.OrderRepository;
import khpi.kvp.webstore_spring.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class OrderController {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    CartService cartService;

    @PostMapping("/payNow")
    public String payNow(@ModelAttribute("orderDTO") OrderDTO orderDTO, HttpSession session, Model model) {
        Map<Product, Integer> cart = cartService.getCart(session);

        Order order = new Order();

        for(Map.Entry<Product, Integer> entry : cart.entrySet()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrders(order);
            orderItem.setProduct(entry.getKey());
            orderItem.setQuantity(entry.getValue());
            order.getOrderItems().add(orderItem);
        }

        order.setFirstName(orderDTO.getFirstName());
        order.setLastName(orderDTO.getLastName());
        order.setAddress1(orderDTO.getAddress1());
        order.setAddress2(orderDTO.getAddress2());
        order.setPostCode(orderDTO.getPostCode());
        order.setPhone(orderDTO.getPhone());
        order.setEmail(orderDTO.getEmail());
        order.setCity(orderDTO.getCity());
        order.setDescription(orderDTO.getDescription());
        orderRepository.save(order);
        return "redirect:/orderPlaced";
    }

    @GetMapping("/orderPlaced")
    public String orderPlaced(Model model, HttpSession session) {
        Map<Product, Integer> cartRecipe = cartService.getCart(session);
        Map<Product, Integer> cartCopy = new HashMap<>(cartRecipe);
        model.addAttribute("cartSet", cartCopy);
        cartService.clear(session);
        return "orderPlaced";
    }
}
