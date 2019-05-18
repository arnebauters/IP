/*package be.ucll.gerechten;

import be.ucll.gerechten.model.Gerecht;
import be.ucll.gerechten.model.TypeGerecht;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FeedbackRealIntegrationTest {

    @LocalServerPort
    private int port;

    // Needed to test REST api
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testAddGerecht() throws JSONException {

        Gerecht ok = new Gerecht("Friet", TypeGerecht.dagschotel, 1);

        HttpEntity<Gerecht> entity = new HttpEntity<Gerecht>(ok, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/gerechten/add"), HttpMethod.POST, entity, String.class);

        String jsonInBodyResponse = "[{ 'name': 'Friet, 'type': 'dagschotel', 'price': 3}]";
        JSONAssert.assertEquals(jsonInBodyResponse, response.getBody(), true);
    }
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}*/
