package nzlzyt.beadando.javagyak.repository;

import jakarta.transaction.Transactional;
import nzlzyt.beadando.javagyak.entity.Kategoria;
import nzlzyt.beadando.javagyak.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KategoriaRepository extends JpaRepository<Kategoria, String> {
}
