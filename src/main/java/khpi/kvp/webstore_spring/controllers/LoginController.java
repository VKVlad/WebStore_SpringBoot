package khpi.kvp.webstore_spring.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import khpi.kvp.webstore_spring.models.Role;
import khpi.kvp.webstore_spring.models.User;
import khpi.kvp.webstore_spring.repositories.ProductRepository;
import khpi.kvp.webstore_spring.repositories.RoleRepository;
import khpi.kvp.webstore_spring.repositories.UserRepository;
import khpi.kvp.webstore_spring.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    CartService cartService;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/login")
    public String login(HttpSession session) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            cartService.clear(session);
            return "login";
        }

        return "redirect:/";
    }
    @GetMapping("/register")
    public String registerGet() {
        return "register";
    }
    @PostMapping("/register")
    public String registerPost(@ModelAttribute("user") User user, HttpServletRequest req) throws ServletException {
        String password = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        List<Role> roles = new ArrayList<>();
        roleRepository.findById(2).ifPresent(roles::add);
        user.setRoles(roles);
        userRepository.save(user);
        req.login(user.getEmail(), password);
        return "redirect:/";
    }
}
