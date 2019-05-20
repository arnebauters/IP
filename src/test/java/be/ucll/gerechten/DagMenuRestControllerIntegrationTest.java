package be.ucll.gerechten;

import be.ucll.gerechten.model.DagMenu;
import be.ucll.gerechten.model.Gerecht;
import be.ucll.gerechten.repository.DagMenuRepository;
import be.ucll.gerechten.repository.GerechtRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class DagMenuRestControllerIntegrationTest {

    @Autowired
    private MockMvc dagMenuController;

    @Autowired
    private DagMenuRepository repository;

    @Autowired
    private GerechtRepository gerechtRepository;

    @Test
    public void givenDagMenus_whenGetDagMenus_thenStatus200AndJSONofDagMenus() throws Exception {
        createTestMenu(LocalDate.parse("2019-05-05"));

        dagMenuController.perform(get("/dagmenus")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].date").value("2019-05-05"))
                .andExpect(jsonPath("$[0].dayName").value("Sunday"))
                .andExpect(jsonPath("$[0].dayOfWeek").value(7))
                .andExpect(jsonPath("$[0].yearAndWeekNumber").value(201918))
                .andExpect(jsonPath("$[0].soep.name").value("soep"))
                .andExpect(jsonPath("$[0].dagschotel.name").value("Spaghetti"))
                .andExpect(jsonPath("$[0].veggie.name").value("veggie"));
    }


    @Test
    public void givenNoMenus_whenAddMenu_thenStatus201AndJSONofMenus() throws Exception {

        dagMenuController.perform(post("/dagmenu/add")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content("{\"day\": \"maandag\", \"date\": \"2019-03-07\", \"soep\": {\"name\": \"tomatensoep\", \"type\": \"soep\", \"price\": 1},\"dagschotel\": {\"name\": \"spaghetti\", \"type\": \"dagschotel\", \"price\": 4.20}, \"veggie\": {\"name\": \"veggie pasta\", \"type\": \"veggie\", \"price\": 4}}"))
                .andExpect(status().is(201))
                .andExpect(jsonPath("$[0].date").value("2019-03-07"))
                .andExpect(jsonPath("$[0].dayName").value("Thursday"))
                .andExpect(jsonPath("$[0].dayOfWeek").value(4))
                .andExpect(jsonPath("$[0].yearAndWeekNumber").value(201910))
                .andExpect(jsonPath("$[0].soep.name").value("tomatensoep"))
                .andExpect(jsonPath("$[0].dagschotel.name").value("spaghetti"))
                .andExpect(jsonPath("$[0].veggie.name").value("veggie pasta"));
    }
    @Test
    public void givenNoMenus_whenAddInvalidMenu_thenStatus400() throws Exception {

        dagMenuController.perform(post("/dagmenu/add")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content("{\"day\": \"maandag\", \"date\": \"2019-03\", \"soep\": {\"name\": \"tomatensoep\", \"type\": \"soep\", \"price\": 1},\"dagschotel\": {\"name\": \"spaghetti\", \"type\": \"dagschotel\", \"price\": 4.20}, \"veggie\": {\"name\": \"veggie pasta\", \"type\": \"veggie\", \"price\": 4}}"))
                .andExpect(status().is(400));

    }

    @Test
    public void givenDagMenus_whenDeleteDagMenu_thenStatus200AndJSONofDagMenus() throws Exception {
        dagMenuController.perform(post("/dagmenu/add")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content("{\"day\": \"maandag\", \"date\": \"2019-03-07\", \"soep\": {\"name\": \"tomatensoep\", \"type\": \"soep\", \"price\": 1},\"dagschotel\": {\"name\": \"spaghetti\", \"type\": \"dagschotel\", \"price\": 4.20}, \"veggie\": {\"name\": \"veggie pasta\", \"type\": \"veggie\", \"price\": 4}}"));

        dagMenuController.perform(delete("/dagmenu/delete/2019-03-07")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
    @Test
    public void givenDagMenus_whenUpdateDagMenu_thenStatus201AndJSONofDagMenus() throws Exception {
        dagMenuController.perform(post("/dagmenu/add")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content("{\"day\": \"maandag\", \"date\": \"2019-03-07\", \"soep\": {\"name\": \"tomatensoep\", \"type\": \"soep\", \"price\": 1},\"dagschotel\": {\"name\": \"spaghetti\", \"type\": \"dagschotel\", \"price\": 4.20}, \"veggie\": {\"name\": \"veggie pasta\", \"type\": \"veggie\", \"price\": 4}}"));

        dagMenuController.perform(patch("/dagmenu/change/2019-03-07")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content("{\"day\": \"maandag\", \"date\": \"2019-03-07\", \"soep\": {\"name\": \"bloemkoolsoep\", \"type\": \"soep\", \"price\": 3},\"dagschotel\": {\"name\": \"Cordon Blue\", \"type\": \"dagschotel\", \"price\": 4}, \"veggie\": {\"name\": \"veggie pasta\", \"type\": \"veggie\", \"price\": 3}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].date").value("2019-03-07"))
                .andExpect(jsonPath("$[0].dayName").value("Thursday"))
                .andExpect(jsonPath("$[0].dayOfWeek").value(4))
                .andExpect(jsonPath("$[0].yearAndWeekNumber").value(201910))
                .andExpect(jsonPath("$[0].soep.name").value("bloemkoolsoep"))
                .andExpect(jsonPath("$[0].dagschotel.name").value("Cordon Blue"))
                .andExpect(jsonPath("$[0].veggie.name").value("veggie pasta"));

    }


    private void createTestMenu(LocalDate date) {
        Gerecht soep = GerechtBuilder.aSoep().build();
        Gerecht veggie = GerechtBuilder.aVeggie().build();
        Gerecht hoofdgerecht = GerechtBuilder.aDagschotel().build();
        DagMenu dagMenu = new DagMenu(date, soep,hoofdgerecht , veggie);
        gerechtRepository.saveAndFlush(soep);
        gerechtRepository.saveAndFlush(hoofdgerecht);
        gerechtRepository.saveAndFlush(veggie);
        repository.saveAndFlush(dagMenu);
    }


}
