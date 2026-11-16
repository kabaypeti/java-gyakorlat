package nzlzyt.beadando.javagyak.repository;

import nzlzyt.beadando.javagyak.entity.Uzenet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UzenetRepository extends JpaRepository<Uzenet, Long> {
    List<Uzenet> findAllByOrderByDatumDesc();
}