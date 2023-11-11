package khpi.kvp.webstore_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class WebStoreSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebStoreSpringApplication.class, args);
    }

}
