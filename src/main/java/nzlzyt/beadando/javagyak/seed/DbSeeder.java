package nzlzyt.beadando.javagyak.seed;

import jakarta.transaction.Transactional;
import nzlzyt.beadando.javagyak.entity.Kategoria;
import nzlzyt.beadando.javagyak.entity.Pizza;
import nzlzyt.beadando.javagyak.entity.Rendeles;
import nzlzyt.beadando.javagyak.repository.KategoriaRepository;
import nzlzyt.beadando.javagyak.repository.PizzaRepository;
import nzlzyt.beadando.javagyak.repository.RendelesRepository;
import nzlzyt.beadando.javagyak.util.DbUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DbSeeder {

    private final KategoriaRepository kategoriaRepository;
    private final PizzaRepository pizzaRepository;
    private final RendelesRepository rendelesRepository;

    public DbSeeder(KategoriaRepository kategoria, PizzaRepository pizza, RendelesRepository rendeles) {
        this.kategoriaRepository = kategoria;
        this.pizzaRepository = pizza;
        this.rendelesRepository = rendeles;
    }

    public void drop() {
        rendelesRepository.deleteAll();
        pizzaRepository.deleteAll();
        kategoriaRepository.deleteAll();
    }

    public void seed() throws FileNotFoundException {

        var root = "./src/main/resources/seed/txt/";
        var f = new File(root+"kategoria.txt");
        DbUtil.seedTableFromFile(new File(root+"kategoria.txt"), kategoriaRepository, map -> {
            return new Kategoria(
                    map.get("nev"),
                    Integer.parseInt(map.get("ar"))
            );
        });

        DbUtil.seedTableFromFile(new File(root+"pizza.txt"), pizzaRepository, map -> {
            return new Pizza(
                    map.get("nev"),
                    kategoriaRepository.getReferenceById(map.get("kategorianev")),
                    Boolean.parseBoolean(map.get("vegetarianus"))
            );
        });

        DbUtil.seedTableFromFile(new File(root+"rendeles.txt"), rendelesRepository, map -> {
            SimpleDateFormat parser = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            try {
                return new Rendeles(
                        pizzaRepository.getReferenceById(map.get("pizzanev")),
                        Integer.parseInt(map.get("darab")),
                        parser.parse(map.get("felvetel")),
                        parser.parse(map.get("kiszallitas"))
                );
            } catch (ParseException e) {
                throw new RuntimeException("Hiba történt dátum formázásakor!!!");
            }
        });

    }

}
