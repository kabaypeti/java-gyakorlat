package nzlzyt.beadando.javagyak.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import nzlzyt.beadando.javagyak.repository.RendelesRepository;
import nzlzyt.beadando.javagyak.repository.results.CountByPizzaResults;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Map;

@Controller
public class DbDiagramController {

    private final RendelesRepository rendelesRepository;

    public DbDiagramController(
            RendelesRepository rendeles
    ) {
        rendelesRepository = rendeles;
    }

    @GetMapping("/dbdiagram")
    public String getDbDiagram(Model model, @RequestParam(value = "sortMode", defaultValue = "NAME_FIRST") SortMode sortMode) {

        //System.out.println(sortMode);

        var data = rendelesRepository.getCountGroupedByNev(sortMode.method);
        var pizzanevData = data.stream().map(CountByPizzaResults::getPizzanev);
        var darabData = data.stream().map(CountByPizzaResults::getDarab);

        var sortModeNames = Map.of(
                SortMode.COUNT_FIRST, "Darabszám szerint",
                SortMode.NAME_FIRST, "Pizza neve szerint"
        );

        model.addAttribute("sortMode", sortMode);
        model.addAttribute("sortModeNames", sortModeNames);
        model.addAttribute("pizzanevData", pizzanevData);
        model.addAttribute("darabData", darabData);

        return "db_diagram";
    }

    public enum SortMode {
        NAME_FIRST(
                Sort.by(Sort.Direction.DESC, "pizzanev").and(
                Sort.by(Sort.Direction.ASC, "darab"))
        ),
        COUNT_FIRST(
                Sort.by(Sort.Direction.ASC, "darab").and(
                Sort.by(Sort.Direction.DESC, "pizzanev"))
        );

        public final Sort method;
        SortMode(Sort method) {
            this.method = method;
        }

        public static SortMode fromText(String text) {
            System.out.println(text);
            for (SortMode mode : SortMode.values()) {
                if (mode.name().equals(text)) {
                    return mode;
                }
            }
            return NAME_FIRST;
        }

        @Override
        public String toString() {
            return this.name();
        }
    }
}
