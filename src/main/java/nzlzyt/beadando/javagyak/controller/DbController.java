package nzlzyt.beadando.javagyak.controller;

import nzlzyt.beadando.javagyak.entity.Kategoria;
import nzlzyt.beadando.javagyak.entity.Pizza;
import nzlzyt.beadando.javagyak.entity.Rendeles;
import nzlzyt.beadando.javagyak.repository.KategoriaRepository;
import nzlzyt.beadando.javagyak.repository.PizzaRepository;
import nzlzyt.beadando.javagyak.repository.RendelesRepository;
import nzlzyt.beadando.javagyak.repository.UserRepository;
import nzlzyt.beadando.javagyak.util.DbUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/db")
public class DbController {

    private final PizzaRepository pizzaRepository;
    private final KategoriaRepository kategoriaRepository;
    private final RendelesRepository rendelesRepository;

    private static final String PAGE = "0";
    private static final String PERPAGE = "10";

    public DbController(
            PizzaRepository pizza,
            KategoriaRepository kategoria,
            RendelesRepository rendeles
    ) {
        pizzaRepository = pizza;
        kategoriaRepository = kategoria;
        rendelesRepository = rendeles;
    }

    public static <T,R extends JpaRepository<T, ?>> void setupDbPage(Model model, R repository, DbUtil.DataConverter<T> converter, List<String> columnNames, int page, int perpage) {
        Pageable pageable = PageRequest.of(page, perpage);
        var data = repository.findAll(pageable);
        var datalist = data.toList();
        var mappedData = DbUtil.mapData(datalist, converter);

        model.addAttribute("page", page);
        model.addAttribute("perPage", perpage);
        model.addAttribute("maxPages", data.getTotalPages()-1);
        model.addAttribute("columnNames", columnNames);
        model.addAttribute("data", mappedData);
    }

    @GetMapping
    public String getDbSelect() {
        return "db_select";
    }

    @GetMapping("/kategoria")
    public String getKategoria(Model model, @RequestParam(value="page",defaultValue=PAGE) int page, @RequestParam(value="perpage",defaultValue=PERPAGE) int perpage) {
        setupDbPage(model, kategoriaRepository,
                input -> List.of(
                    input.getNev(),
                    input.getAr()
        ), List.of(
            "Kategória neve", "Ár"
        ), page, perpage);
        model.addAttribute("tableName", "Pizza kategóriák");
        return "db_view";
    }

    @GetMapping("/pizza")
    public String getPizza(Model model, @RequestParam(value="page",defaultValue=PAGE) int page, @RequestParam(value="perpage",defaultValue=PERPAGE) int perpage) {
        setupDbPage(model, pizzaRepository,
                input -> List.of(
                        input.getNev(),
                        input.getKategoria().getNev(),
                        input.getVegetarianus() ? "Igen" : "Nem"
        ), List.of(
            "Pizza neve", "Kategória neve", "Vegetáriánus?"
        ), page, perpage);
        model.addAttribute("tableName", "Pizzák");
        return "db_view";
    }

    @GetMapping("/rendeles")
    public String getRendeles(Model model, @RequestParam(value="page",defaultValue=PAGE) int page, @RequestParam(value="perpage",defaultValue=PERPAGE) int perpage) {
        setupDbPage(model, rendelesRepository,
                input -> List.of(
                    input.getPizza().getNev(),
                    input.getDarab(),
                    input.getFelvetel(),
                    input.getKiszallitas()
        ), List.of(
            "Pizza neve", "Darabszám", "Felvétel dátuma", "Kiszállítás dátuma"
        ), page, perpage);
        model.addAttribute("tableName", "Rendelések");
        return "db_view";
    }

}