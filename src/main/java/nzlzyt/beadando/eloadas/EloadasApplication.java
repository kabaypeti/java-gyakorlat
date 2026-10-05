package nzlzyt.beadando.eloadas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EloadasApplication {

    public static void main(String[] args) {
        SpringApplication.run(EloadasApplication.class, args);
    }

    @GetMapping("/")
    public String print1() {
        return "<h1>Semmi</h1>";
    }

}
