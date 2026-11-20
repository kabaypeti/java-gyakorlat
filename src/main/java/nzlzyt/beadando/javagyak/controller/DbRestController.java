package nzlzyt.beadando.javagyak.controller;

import nzlzyt.beadando.javagyak.dto.RendelesRequest;
import nzlzyt.beadando.javagyak.dto.RendelesResponse;
import nzlzyt.beadando.javagyak.entity.Rendeles;
import nzlzyt.beadando.javagyak.repository.PizzaRepository;
import nzlzyt.beadando.javagyak.repository.RendelesRepository;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DbRestController {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
    public List<RendelesResponse> apiFindAllRendeles() {
        var list = rendelesRepository.findAll();
        var list2 = new ArrayList<RendelesResponse>();
        for (var elem : list) {
            list2.add(mapToResponse(elem));
        }
        return list2;
    }

    @PostMapping("/rendeles")
    public Optional<Rendeles> apiCreateRendeles(@RequestBody RendelesRequest rendeles) throws ParseException {
        var pizza = pizzaRepository.findById(rendeles.getPizzanev());
        if (pizza.isPresent()) {
            var obj = mapFromRequest(rendeles);
            if (obj.isPresent()) {
                return Optional.of(rendelesRepository.save(obj.get()));
            }
        }
        return Optional.empty();
    }

    @GetMapping("/rendeles/{id}")
    public Optional<RendelesResponse> apiFindRendelesById(@PathVariable Long id) {
        var rendeles = rendelesRepository.findById(id);
        return rendeles.map(this::mapToResponse);
    }

    @PutMapping("/rendeles/{id}")
    public Optional<Rendeles> apiUpdateRendeles(@RequestBody RendelesRequest rendeles, @PathVariable Long id) throws ParseException {
        var obj = mapFromRequest(rendeles);
        if (obj.isPresent()) {
            var result = rendelesRepository.findById(id)
                    .map(r -> {
                        r.setPizza(obj.get().getPizza());
                        r.setDarab(obj.get().getDarab());
                        r.setFelvetel(obj.get().getFelvetel());
                        r.setKiszallitas(obj.get().getKiszallitas());
                        return rendelesRepository.save(r);
                    });
            if (result.isPresent()) {
                return result;
            }
        }
        return Optional.empty();
    }

    @DeleteMapping("/rendeles/{id}")
    public void apiDeleteRendeles(@PathVariable Long id) {
        rendelesRepository.deleteById(id);
    }

    private RendelesResponse mapToResponse(Rendeles rendeles) {
        return new RendelesResponse(
                rendeles.getId(),
                rendeles.getPizza().getNev(),
                rendeles.getDarab(),
                rendeles.getFelvetel().toString(),
                rendeles.getKiszallitas().toString()
        );
    }

    private Optional<Rendeles> mapFromRequest(RendelesRequest rendeles) throws ParseException {
        var pizza = pizzaRepository.findById(rendeles.getPizzanev());
        if (pizza.isPresent()) {
            return Optional.of(new Rendeles(
                    pizza.get(),
                    rendeles.getDarab(),
                    DATE_FORMAT.parse(rendeles.getFelvetel()),
                    DATE_FORMAT.parse(rendeles.getKiszallitas())
            ));
        }
        return Optional.empty();
    }

}
