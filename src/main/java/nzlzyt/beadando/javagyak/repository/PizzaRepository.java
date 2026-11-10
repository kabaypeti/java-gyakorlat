package nzlzyt.beadando.javagyak.repository;

import nzlzyt.beadando.javagyak.entity.Pizza;
import nzlzyt.beadando.javagyak.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends JpaRepository<Pizza, String> {
}
