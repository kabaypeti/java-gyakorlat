package nzlzyt.beadando.javagyak.controller;

import nzlzyt.beadando.javagyak.dto.RendelesRequest;
import nzlzyt.beadando.javagyak.entity.Pizza;
import nzlzyt.beadando.javagyak.entity.Rendeles;
import nzlzyt.beadando.javagyak.repository.KategoriaRepository;
import nzlzyt.beadando.javagyak.repository.PizzaRepository;
import nzlzyt.beadando.javagyak.repository.RendelesRepository;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DbRestController {

    private final RendelesRepository rendelesRepository;
    private final PizzaRepository pizzaRepository;

    public DbRestController(
            RendelesRepository rendeles,
            PizzaRepository pizza
    ) {
        rendelesRepository = rendeles;
        pizzaRepository = pizza;
    }

    @GetMapping("/rendeles")
    public List<Rendeles> apiFindAllRendeles() {
        return rendelesRepository.findAll();
    }

    @PostMapping("/rendeles")
    public Rendeles apiCreateRendeles(@RequestBody RendelesRequest rendeles) throws ParseException {
        var pizza = pizzaRepository.findById(rendeles.getPizzanev());
        if (pizza.isPresent()) {
            return rendelesRepository.save(
                new Rendeles(
                    pizza.get(),
                    rendeles.getDarab(),
                    DateFormat.getInstance().parse(rendeles.getFelvetel()),
                    DateFormat.getInstance().parse(rendeles.getKiszallitas())
                )
            );
        }
        return null;
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
