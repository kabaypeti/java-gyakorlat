package nzlzyt.beadando.javagyak.repository;

import nzlzyt.beadando.javagyak.entity.Pizza;
import nzlzyt.beadando.javagyak.entity.Rendeles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RendelesRepository extends JpaRepository<Rendeles, Long> {
}
