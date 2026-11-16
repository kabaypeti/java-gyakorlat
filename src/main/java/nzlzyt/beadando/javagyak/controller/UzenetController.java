package nzlzyt.beadando.javagyak.controller;

import nzlzyt.beadando.javagyak.entity.Uzenet;
import nzlzyt.beadando.javagyak.repository.UzenetRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
public class UzenetController {

    private final UzenetRepository uzenetRepository;

    public UzenetController(UzenetRepository uzenetRepository) {
        this.uzenetRepository = uzenetRepository;
    }

    @GetMapping("/kapcsolat")
    public String showKapcsolatForm(Model model) {
        model.addAttribute("uzenet", "");
        return "kapcsolat";
    }

    @PostMapping("/kapcsolat")
    public String sendUzenet(@RequestParam("uzenet") String uzenet, Principal principal, Model model) {
        String trimmedUzenet = (uzenet != null) ? uzenet.trim() : "";

        if (trimmedUzenet.isEmpty()) {
            model.addAttribute("error", "Az üzenet szövege nem lehet üres!");
            model.addAttribute("uzenet", trimmedUzenet);
            return "kapcsolat";
        }

        String kuldo = (principal != null) ? principal.getName() : "Vendég";
        uzenetRepository.save(new Uzenet(kuldo, trimmedUzenet, LocalDateTime.now()));

        model.addAttribute("success", "Az üzenetedet sikeresen elküldtük!");
        model.addAttribute("uzenet", "");
        return "kapcsolat";
    }

    private static final String PAGE = "0";
    private static final String PERPAGE = "10";

    @GetMapping("/uzenetek")
    public String listUzenetek(Model model, @RequestParam(value = "page", defaultValue = PAGE) int page, @RequestParam(value = "perpage", defaultValue = PERPAGE) int perpage) {
        Pageable pageable = PageRequest.of(page, perpage, Sort.by(Sort.Direction.DESC, "datum"));
        var data = uzenetRepository.findAll(pageable);

        model.addAttribute("uzenetek", data.toList());
        model.addAttribute("page", page);
        model.addAttribute("perPage", perpage);
        model.addAttribute("maxPages", data.getTotalPages() - 1);

        return "uzenetek";
    }
}
