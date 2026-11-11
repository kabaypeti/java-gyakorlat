package nzlzyt.beadando.javagyak.repository;

import jakarta.transaction.Transactional;
import nzlzyt.beadando.javagyak.entity.Rendeles;
import nzlzyt.beadando.javagyak.repository.results.CountByPizzaResults;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RendelesRepository extends JpaRepository<Rendeles, Long> {

    @Transactional
    @Query(value="""
        SELECT new nzlzyt.beadando.javagyak.repository.results.CountByPizzaResults(r.pizza.nev AS pizzanev, sum(r.darab) AS darab)
        FROM rendeles r
        GROUP BY r.pizza.nev
    """)
    List<CountByPizzaResults> getCountGroupedByNev(Sort sort);
}
