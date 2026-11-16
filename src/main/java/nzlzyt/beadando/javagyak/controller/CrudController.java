package nzlzyt.beadando.javagyak.controller;

import nzlzyt.beadando.javagyak.entity.Kategoria;
import nzlzyt.beadando.javagyak.repository.KategoriaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequestMapping("/crud")
public class CrudController {

    private final KategoriaRepository kategoriaRepository;

    private static final String PAGE = "0";
    private static final String PERPAGE = "10";

    public CrudController(KategoriaRepository kategoriaRepository) {
        this.kategoriaRepository = kategoriaRepository;
    }

    @GetMapping
    public String getCrud(Model model, @RequestParam(value="page",defaultValue=PAGE) int page, @RequestParam(value="perpage",defaultValue=PERPAGE) int perpage) {
        DbController.setupDbPage(model, kategoriaRepository,
                input -> List.of(
                        input.getNev(),
                        input.getAr()
                ), List.of(
                        "Kategória neve", "Ár"
                ), page, perpage);

        model.addAttribute("kategoriak", kategoriaRepository.findAll(PageRequest.of(page, perpage)).toList());
        return "crud";
    }

    @GetMapping("/uj")
    public String showCreateForm(Model model) {
        model.addAttribute("nev", "");
        model.addAttribute("ar", "");
        return "crud_create";
    }

    @PostMapping("/uj")
    public String createKategoria(@RequestParam("nev") String nev, @RequestParam("ar") Integer ar, Model model) {
        String trimmedNev = (nev != null) ? nev.trim() : "";
        String error = validateKategoria(trimmedNev, ar, true);
        if (error != null) { return returnWithError("crud_create", error, trimmedNev, ar, model); }
        kategoriaRepository.save(new Kategoria(trimmedNev, ar));
        return "redirect:/crud";
    }

    //szerveroldali hibakezelés

    private String validateKategoria(String nev, Integer ar, boolean checkDuplicate) {
        if (nev == null || nev.isEmpty()) { return "A kategória neve nem lehet üres!"; }
        if (checkDuplicate && kategoriaRepository.existsById(nev)) { return "Ilyen nevű kategória már létezik: " + nev; }
        if (ar == null || ar <= 0) { return "Az árnak 0-nál nagyobb pozitív számnak kell lennie!"; }
        return null;
    }

    private String returnWithError(String view, String error, String nev, Integer ar, Model model) {
        model.addAttribute("error", error);
        model.addAttribute("nev", nev);
        model.addAttribute("ar", ar);
        return view;
    }
}