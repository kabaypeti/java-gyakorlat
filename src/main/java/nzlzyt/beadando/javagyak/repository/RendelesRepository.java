package nzlzyt.beadando.javagyak.repository;

import nzlzyt.beadando.javagyak.entity.Pizza;
import nzlzyt.beadando.javagyak.entity.Rendeles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RendelesRepository extends JpaRepository<Rendeles, Long> {
}
