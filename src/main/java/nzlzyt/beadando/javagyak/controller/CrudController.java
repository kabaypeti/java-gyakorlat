package nzlzyt.beadando.javagyak.controller;

import nzlzyt.beadando.javagyak.repository.KategoriaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}