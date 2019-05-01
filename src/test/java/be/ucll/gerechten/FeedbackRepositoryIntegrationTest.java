/*package be.ucll.gerechten;

import be.ucll.feedback.model.Feedback;
import be.ucll.feedback.repository.FeedbackRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
// Needed for testing the persistence layer
// Here using the in-memory DB H2
@DataJpaTest
public class FeedbackRepositoryIntegrationTest {

    // Needed to add some data already in our DB to test things
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Test
    public void should_get_all_feedbacks () {
        // given
        Feedback ok = GerechtBuilder.anOKFeedback().build();
        // puts objects into the in-memory DB
        entityManager.persist(ok);
        entityManager.flush();

        Feedback nok = new Feedback("Stijn", "This is NOK");
        entityManager.persist(nok);
        entityManager.flush();

        // when
        List<Feedback> feedbacksFound = feedbackRepository.findAll();

        // then
        assertThat(feedbacksFound.size() == 2);
        assertThat(feedbacksFound.contains(ok));
        assertThat(feedbacksFound.contains(nok));
    }

    @Test
    public void should_find_feedback_by_given_name () {
        // given
        Feedback ok = new Feedback("Elke", "OK well done");
        entityManager.persist(ok);
        entityManager.flush();

        // when
        Feedback found = feedbackRepository.findFeedbackByName(ok.getName());

        // then
        assertThat(found.getName()).isEqualTo(ok.getName());
        assertThat(found.getFeedback()).isEqualTo(ok.getFeedback());
    }

    @Test
    public void should_add_feedback () {
        // given
        Feedback ok = new Feedback("Elke", "OK well done");

        // when
        Feedback addedFeedback = feedbackRepository.save(ok);

        // then
        assertThat(addedFeedback.getName()).isEqualTo("Elke");
        assertThat(addedFeedback.getFeedback()).isEqualTo("OK well done");
    }
}
*/