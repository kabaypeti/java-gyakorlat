package nzlzyt.beadando.javagyak;

import nzlzyt.beadando.javagyak.repository.KategoriaRepository;
import nzlzyt.beadando.javagyak.repository.PizzaRepository;
import nzlzyt.beadando.javagyak.repository.RendelesRepository;
import nzlzyt.beadando.javagyak.seed.DbSeeder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(KategoriaRepository kategoria, PizzaRepository pizza, RendelesRepository rendeles) {
        return args -> {
            var seeder = new DbSeeder(kategoria, pizza, rendeles);
            seeder.drop();
            seeder.seed();
        };
    }
}