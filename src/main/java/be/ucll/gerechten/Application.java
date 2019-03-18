package be.ucll.gerechten;

import be.ucll.gerechten.model.DagMenu;
import be.ucll.gerechten.model.Gerecht;
import be.ucll.gerechten.repository.GerechtRepository;
import be.ucll.gerechten.repository.MenuRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @Order(1)
    CommandLineRunner runnerGerechten(GerechtRepository repo) {
        return GerechtArgs -> {
            repo.save(new Gerecht(5, "Spaghetti", "dagschotel"));
            repo.save(new Gerecht(2.5, "Tomatensoep met balletjes", "dagschotel"));
            repo.save(new Gerecht(5.5, "Vol-aux-vents", "dagschotel"));
        };
    }

    @Bean
    @Order(2)
    CommandLineRunner runnerMenu(MenuRepository repository) {
        return MenuArgs -> {

            DagMenu menu1 = new DagMenu("MONDAY", "04-03-2019");
            DagMenu menu2 = new DagMenu("TUESDAY", "05-03-2019");
            menu1.setDagschotel(new Gerecht(5, "Spaghetti", "dagschotel"));
            menu1.setSoep(new Gerecht(2.5, "Tomatensoep met balletjes", "soep"));
            menu1.setVeggie(new Gerecht(5.5, "Vol-aux-vents", "veggie"));
            menu2.setDagschotel(new Gerecht(5, "Spaghetti", "dagschotel"));
            menu2.setSoep(new Gerecht(2.5, "Tomatensoep met balletjes", "soep"));
            menu2.setVeggie(new Gerecht(5.5, "Vol-aux-vents", "veggie"));
            repository.save(menu1);
            repository.save(menu2);
        };
    }
}

