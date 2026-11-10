package nzlzyt.beadando.javagyak.controller;

import nzlzyt.beadando.javagyak.entity.Kategoria;
import nzlzyt.beadando.javagyak.entity.Pizza;
import nzlzyt.beadando.javagyak.entity.Rendeles;
import nzlzyt.beadando.javagyak.repository.KategoriaRepository;
import nzlzyt.beadando.javagyak.repository.PizzaRepository;
import nzlzyt.beadando.javagyak.repository.RendelesRepository;
import nzlzyt.beadando.javagyak.repository.UserRepository;
import nzlzyt.beadando.javagyak.util.DbUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/db")
public class DbController {

    private final PizzaRepository pizzaRepository;
    private final KategoriaRepository kategoriaRepository;
    private final RendelesRepository rendelesRepository;

    public DbController(
            PizzaRepository pizza,
            KategoriaRepository kategoria,
            RendelesRepository rendeles
    ) {
        pizzaRepository = pizza;
        kategoriaRepository = kategoria;
        rendelesRepository = rendeles;
    }

    @GetMapping("/kategoria")
    public String getKategoria(Model model) {
        var data = kategoriaRepository.findAll();
        var mappedData = DbUtil.mapData(data, List.of(
            Kategoria::getNev,
            Kategoria::getAr
        ));
        model.addAttribute("columnNames", List.of("Kategória neve", "Ár"));
        model.addAttribute("data", mappedData);
        return "db_view";
    }

    @GetMapping("/pizza")
    public String getPizza(Model model) {
        var data = pizzaRepository.findAll();
        var mappedData = DbUtil.mapData(data, List.of(
            Pizza::getNev,
            pizza -> pizza.getKategoria().getNev(),
            pizza -> pizza.getVegetarianus() ? "Igen" : "Nem"
        ));
        model.addAttribute("columnNames", List.of("Pizza neve", "Kategória neve", "Vegetáriánus?"));
        model.addAttribute("data", mappedData);
        return "db_view";
    }

    @GetMapping("/rendeles")
    public String getRendeles(Model model) {
        var data = rendelesRepository.findAll();
        var mappedData = DbUtil.mapData(data, List.of(
                rendeles -> rendeles.getPizza().getNev(),
                Rendeles::getDarab,
                Rendeles::getFelvetel,
                Rendeles::getKiszallitas
        ));
        model.addAttribute("columnNames", List.of("Pizza neve", "Darabszám", "Felvétel dátuma", "Kiszállítás dátuma"));
        model.addAttribute("data", mappedData);
        return "db_view";
    }

}