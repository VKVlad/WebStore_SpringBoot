package khpi.kvp.webstore_spring.controllers;

import jakarta.mail.MessagingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import khpi.kvp.webstore_spring.models.Role;
import khpi.kvp.webstore_spring.models.User;
import khpi.kvp.webstore_spring.repositories.ProductRepository;
import khpi.kvp.webstore_spring.repositories.RoleRepository;
import khpi.kvp.webstore_spring.repositories.UserRepository;
import khpi.kvp.webstore_spring.services.CartService;
import khpi.kvp.webstore_spring.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class LoginController {
    @Autowired
    EmailService emailService;
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
    public String registerPost(@ModelAttribute("user") User user, HttpServletRequest req, Model model) throws ServletException, MessagingException, IOException {
        String email = user.getEmail();

        if (userRepository.getUserByEmail(email).isPresent()) {
            model.addAttribute("error", "User with this email already exists.");
            return "register";
        }
        String password = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(password));

        String confirmationCode = generateConfirmationCode();
        user.setConfirmationCode(confirmationCode);

        user.setConfirmed(false);

        List<Role> roles = new ArrayList<>();
        roleRepository.findById(2).ifPresent(roles::add);
        user.setRoles(roles);

        emailService.sendEmailFromTemplate(user.getEmail(), user.getConfirmationCode());
        userRepository.save(user);

        req.login(user.getEmail(), password);
        return "redirect:/confirmation-page/" + user.getEmail();
    }
    @PostMapping("/confirm-registration")
    public String confirmRegistration(@RequestParam String email, @RequestParam String confirmationCode, Model model) {
        model.addAttribute("email", email);
        User user = userRepository.getUserByEmail(email).get();
        if (user != null && user.getConfirmationCode().equals(confirmationCode)) {
            user.setConfirmed(true);
            userRepository.save(user);
            return "redirect:/";
        } else {
            return "/confirmation-page";
        }
    }
    @GetMapping("/confirmation-page/{email}")
    public String confirmationPage(@PathVariable("email") String email, Model model) {
        model.addAttribute("email", email);
        return "confirmation-page";
    }
    public String generateConfirmationCode() {
        String code = UUID.randomUUID().toString().replaceAll("-", "");
        code = code.substring(0, 10);
        return code;
    }
}
