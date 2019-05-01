package be.ucll.gerechten;

import be.ucll.gerechten.model.Gerecht;
import be.ucll.gerechten.model.MyService;
import be.ucll.gerechten.model.TypeGerecht;
import be.ucll.gerechten.repository.DagMenuRepository;
import be.ucll.gerechten.repository.GerechtRepository;
import be.ucll.gerechten.repository.MenuRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


// Bridge between Spring Boot test features and JUnit
@RunWith(SpringRunner.class)
public class ServiceUnitTest {

    @TestConfiguration
    static class ServiceUnitTestContextConfiguration {

        // Creates an instance of service in order to be able to autowire it
        @Bean
        public MyService service() {
            return new MyService();
        }
    }

    @Autowired
    private MyService myService;

    // Service is dependent of Repository, however implementation of Repository is
    // not important => therefor mock it
    // Mock support of Spring Boot Test
    @MockBean
    private GerechtRepository gerechtRepository;
    @MockBean
    private DagMenuRepository dagMenuRepository;
    @MockBean
    private MenuRepository menuRepository;

    private Gerecht ok, nok, maybe, deleted;
    private List<Gerecht> gerechts;

    @Before
    public void setUp() {
        ok = new Gerecht("friet", TypeGerecht.dagschotel, 1);
        nok = new Gerecht("nokgerecht",TypeGerecht.dagschotel, 0);
        maybe = new Gerecht("prijs", TypeGerecht.dagschotel, 0.1);
        deleted = new Gerecht("verwijdert", TypeGerecht.soep, 10);
        gerechts = new ArrayList<Gerecht>();
        gerechts.add(ok);
        gerechts.add(nok);
        gerechts.add(deleted);
    }

    @Test
    public void should_find_Gerecht_by_given_name () {
        // Mock
        // When we ask at the repo for the ok feedback, it will return it
        Mockito.when(gerechtRepository.findByName(ok.getName())).thenReturn(ok);

        // given
        String name = "friet";
        double price = 1;
        TypeGerecht type = TypeGerecht.dagschotel;

        // when
        Gerecht found = myService.findGerechtByName(name);

        // then
        assertThat(found.getName()).isEqualTo(name);
        assertThat(found.getPrice()).isEqualTo(price);
        assertThat(found.getType()).isEqualTo(type);
    }

    @Test
    public void should_get_all_Gerechts () {
        // Mock
        Mockito.when(gerechtRepository.findAll()).thenReturn(gerechts);

        //when
        List<Gerecht> foundGerechts = myService.getAllGerechten();

        //then
        assertThat(foundGerechts.size()).isEqualTo(3);
        assertThat(foundGerechts).contains(ok);
        assertThat(foundGerechts).contains(nok);
        assertThat(foundGerechts).contains(deleted);
    }

    @Test
    public void should_add_gerecht () {
        //Mock
        Mockito.when(gerechtRepository.save(maybe)).thenReturn(maybe);

        //when
        Gerecht addedGerecht = myService.addGerecht(maybe);

        //then
        assertThat(addedGerecht.getName()).isEqualTo("prijs");
        assertThat(addedGerecht.getPrice()).isEqualTo(0.1);
        assertThat(addedGerecht.getPrice()).isEqualTo(TypeGerecht.dagschotel);
    }
}
