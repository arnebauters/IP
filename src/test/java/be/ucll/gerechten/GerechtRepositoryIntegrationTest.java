package be.ucll.gerechten;

import be.ucll.gerechten.model.Gerecht;
import be.ucll.gerechten.model.TypeGerecht;
import be.ucll.gerechten.repository.GerechtRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
// Needed for testing the persistence layer
// Here using the in-memory DB H2
@DataJpaTest
public class GerechtRepositoryIntegrationTest {

    // Needed to add some data already in our DB to test things
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GerechtRepository gerechtRepository;



    @Test
    public void should_get_all_gerechten () {
        // given
        Gerecht ok = GerechtBuilder.anOkGerecht().build();
        // puts objects into the in-memory DB
        entityManager.persist(ok);
        entityManager.flush();

        Gerecht gerecht = new Gerecht("nogEenGerecht", TypeGerecht.dagschotel,2);
        entityManager.persist(gerecht);
        entityManager.flush();

        // when
        List<Gerecht> foundGerechten = gerechtRepository.findAll();

        // then
        assertThat(foundGerechten.size() == 2);
        assertThat(foundGerechten.contains(ok));
        assertThat(foundGerechten.contains(gerecht));
    }

    @Test
    public void should_find_gerecht_by_given_name () {
        // given
        Gerecht ok = new Gerecht("okGerecht", TypeGerecht.dagschotel, 3);
        entityManager.persist(ok);
        entityManager.flush();

        // when
        Gerecht found = gerechtRepository.findByName(ok.getName());

        // then
        assertThat(found.getName()).isEqualTo(ok.getName());
        assertThat(found.getType()).isEqualTo(ok.getType());
        assertThat(found.getPrice()).isEqualTo(ok.getPrice());
    }

    @Test
    public void should_add_gerecht () {
        // given
        Gerecht ok = new Gerecht("okGerecht", TypeGerecht.veggie, 4);

        // when
        Gerecht addedGerecht = gerechtRepository.save(ok);

        // then
        assertThat(addedGerecht.getName()).isEqualTo("okGerecht");
        assertThat(addedGerecht.getType()).isEqualTo(TypeGerecht.veggie);
        assertThat(addedGerecht.getPrice()).isEqualTo(4);
    }

    /*@Test(expected = PersistenceException.class)
    public void should_not_add_gerecht() {

        //given

        Gerecht prijsTeGroot = new Gerecht("prijstegroot", TypeGerecht.soep, 10.1);
        Gerecht prijsTeKlein = new Gerecht("prijsTeKlein", TypeGerecht.veggie, 0.0);
        Gerecht naamTeKort = new Gerecht("nok", TypeGerecht.dagschotel, 5);
        Gerecht naamTeLang = new Gerecht("dezenaamisveeltelang", TypeGerecht.dagschotel, 5);

        //when
        gerechtRepository.save(prijsTeGroot);
        gerechtRepository.save(prijsTeKlein);
        gerechtRepository.save(naamTeKort);
        gerechtRepository.save(naamTeLang);

        //then
    }*/

    @Test(expected = IllegalArgumentException.class)
    public void given_gerecht_should_be_removed (){
        Gerecht ok = new Gerecht("okGerecht", TypeGerecht.veggie, 4);
        gerechtRepository.save(ok);
        gerechtRepository.delete(ok);
        gerechtRepository.findByName(ok.getName());
    }
}
