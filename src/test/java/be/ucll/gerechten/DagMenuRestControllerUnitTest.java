package be.ucll.gerechten;

import be.ucll.gerechten.controller.DagMenuController;
import be.ucll.gerechten.controller.GerechtController;
import be.ucll.gerechten.model.DagMenu;
import be.ucll.gerechten.model.Gerecht;
import be.ucll.gerechten.model.MyService;
import be.ucll.gerechten.model.TypeGerecht;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
// Bootstraps the controller and auto-configures MockMvc, this are testing controllers
// without starting a full HTTP server
@WebMvcTest(DagMenuController.class)
public class DagMenuRestControllerUnitTest {

    @Autowired
    private MockMvc dagMenuController;

    @MockBean
    private MyService service;

    @Test
    public void givenTwoGerechts_whenGetGerechts_thenReturnJsonArray() throws Exception {
        Gerecht soep = GerechtBuilder.aSoep().build();
        Gerecht veggie = GerechtBuilder.aVeggie().build();
        Gerecht dagschotel = GerechtBuilder.aDagschotel().build();
        Gerecht nok = new Gerecht("bla", TypeGerecht.soep, 0);
        DagMenu ok = new DagMenu(LocalDate.parse("2019-03-07"),soep, dagschotel, veggie );
        DagMenu nokMenu = new DagMenu(LocalDate.now(), nok, dagschotel, veggie );
        when(service.getDagmenus()).thenReturn(Arrays.asList(ok, nokMenu));

        dagMenuController.perform(get("/dagmenus")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].date").value("2019-03-07"))
                .andExpect(jsonPath("$[0].dayName").value("Thursday"))
                .andExpect(jsonPath("$[0].dayOfWeek").value(4))
                .andExpect(jsonPath("$[0].yearAndWeekNumber").value(201910));
    }

    @Test
    public void givenNoDagMenus_whenAddDagMenu_thenReturnJsonArray() throws Exception {
        DagMenu ok = MenuBuilder.anOkMenu().build();
        when(service.getDagmenus()).thenReturn(Arrays.asList(ok));

        dagMenuController.perform(post("/dagmenu/add")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content("{\"\"day\": \"maandag\", \"date\": \"2019-03-07\", \"soep\": {\"name\": \"tomatensoep\", \"type\": \"soep\", \"price\": 1},\"dagschotel\": {\"name\": \"spaghetti\", \"type\": \"dagschotel\", \"price\": 4.20}, \"veggie\": {\"name\": \"veggie pasta\", \"type\": \"veggie\", \"price\": 4}"))
                //.andDo(print()) // with this you print the request and response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].date").value(ok.getDate()));
    }

    @Test
    public void givenNoDagMenus_whenAddDagMenuWithNoValidDate_thenReturnJsonArray() throws Exception {
        dagMenuController.perform(post("/dagmenu/add")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content("{\"day\": \"maandag\", \"date\": \"2019-03-07\", \"soep\": {\"name\": \"tomatensoep\", \"type\": \"soep\", \"price\": 1},\"dagschotel\": {\"name\": \"spaghetti\", \"type\": \"dagschotel\", \"price\": 4.20}, \"veggie\": {\"name\": \"veggie pasta\", \"type\": \"veggie\", \"price\": 4}"))
                //.andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("size must be between 5 and 80"));
    }

}
