package nzlzyt.beadando.javagyak.controller;

import nzlzyt.beadando.javagyak.entity.Pizza;
import nzlzyt.beadando.javagyak.entity.Rendeles;
import nzlzyt.beadando.javagyak.repository.KategoriaRepository;
import nzlzyt.beadando.javagyak.repository.PizzaRepository;
import nzlzyt.beadando.javagyak.repository.RendelesRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DbRestController {

    private final RendelesRepository rendelesRepository;

    public DbRestController(
            RendelesRepository rendeles
    ) {
        rendelesRepository = rendeles;
    }

    @GetMapping("/rendeles")
    public List<Rendeles> apiFindAllRendeles() {
        return rendelesRepository.findAll();
    }

    @PostMapping("/rendeles")
    public Rendeles apiCreateRendeles(@RequestBody Rendeles rendeles) {
        return rendelesRepository.save(rendeles);
    }

    @GetMapping("/rendeles/{id}")
    public Optional<Rendeles> apiFindRendelesById(@PathVariable Long id) {
        return rendelesRepository.findById(id);
    }

    @PutMapping("/rendeles/{id}")
    public Rendeles apiUpdateRendeles(@RequestBody Rendeles rendeles, @PathVariable Long id) {
        return rendelesRepository.findById(id)
                .map(r -> {
                    r.setPizza(rendeles.getPizza());
                    r.setDarab(rendeles.getDarab());
                    r.setFelvetel(rendeles.getFelvetel());
                    r.setKiszallitas(rendeles.getKiszallitas());
                    return rendelesRepository.save(r);
                }).orElseGet(() -> {
                    rendeles.setId(id);
                    return rendelesRepository.save(rendeles);
                });
    }

    @DeleteMapping("/rendeles/{id}")
    public void apiDeleteRendeles(@PathVariable Long id) {
        rendelesRepository.deleteById(id);
    }

}
