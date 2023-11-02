package khpi.kvp.webstore_spring.controllers;

import khpi.kvp.webstore_spring.repositories.ProductRepository;
import khpi.kvp.webstore_spring.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class ProductController {
    @Autowired
    ProductService productService;

}
