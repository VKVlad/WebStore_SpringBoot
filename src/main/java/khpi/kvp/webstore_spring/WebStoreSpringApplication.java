package khpi.kvp.webstore_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
@RestController
public class WebStoreSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebStoreSpringApplication.class, args);
    }

}
