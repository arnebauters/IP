package be.ucll.gerechten;

import be.ucll.gerechten.controller.DagMenuController;
import be.ucll.gerechten.controller.GerechtController;
import be.ucll.gerechten.model.DagMenu;
import be.ucll.gerechten.model.Gerecht;
import be.ucll.gerechten.model.MyService;
import be.ucll.gerechten.model.TypeGerecht;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    protected String mapToJson(DagMenu obj) throws JsonProcessingException {
        return "{\"day\": \""+obj.getDayName()+"\", \"date\": \""+obj.getDate()+"\", \"soep\": {\"name\": \""+obj.getSoep().getName()+"\", \"type\": \""+obj.getSoep().getType()+"\", \"price\": "+obj.getSoep().getPrice()+"},\"dagschotel\": {\"name\": \""+obj.getDagschotel().getName()+"\", \"type\": \"dagschotel\", \"price\": "+obj.getDagschotel().getPrice()+"}, \"veggie\": {\"name\": \""+obj.getVeggie().getName()+"\", \"type\": \"veggie\", \"price\": "+obj.getVeggie().getPrice()+"}}";
    }
    @Test
    public void givenOneMenu_whenGetMenus_thenReturnJsonArray() throws Exception {
        Gerecht soep = GerechtBuilder.aSoep().build();
        Gerecht veggie = GerechtBuilder.aVeggie().build();
        Gerecht dagschotel = GerechtBuilder.aDagschotel().build();
        DagMenu ok = new DagMenu(LocalDate.parse("2019-03-07"),soep, dagschotel, veggie );
        when(service.getDagmenus()).thenReturn(Arrays.asList(ok));

        dagMenuController.perform(get("/dagmenus")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].date").value("2019-03-07"))
                .andExpect(jsonPath("$[0].dayName").value("Thursday"))
                .andExpect(jsonPath("$[0].dayOfWeek").value(4))
                .andExpect(jsonPath("$[0].yearAndWeekNumber").value(201910));
    }

    @Test
    public void givenOneMenu_whenAddMenu_thenReturnJsonArray() throws Exception {
        Gerecht soep = GerechtBuilder.aSoep().build();
        Gerecht veggie = GerechtBuilder.aVeggie().build();
        Gerecht dagschotel = GerechtBuilder.aDagschotel().build();
        Gerecht nok = new Gerecht("blabla", TypeGerecht.soep, 2);
        DagMenu ok = new DagMenu(LocalDate.parse("2019-03-07"),soep, dagschotel, veggie );
        DagMenu nokMenu = new DagMenu(LocalDate.parse("2019-04-07"), nok, dagschotel, veggie );
        when(service.getDagmenus()).thenReturn(Arrays.asList(ok, nokMenu));
        String json = mapToJson(nokMenu);
        dagMenuController.perform(post("/dagmenu/add")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andExpect(status().is(201))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].date").value("2019-04-07"))
                .andExpect(jsonPath("$[1].dayName").value("Sunday"))
                .andExpect(jsonPath("$[1].dayOfWeek").value(7))
                .andExpect(jsonPath("$[1].yearAndWeekNumber").value(201914));

    }

    @Test
    public void givenTwoMenu_whenChangeMeal_thenReturnJsonArray() throws Exception {
        Gerecht soep = GerechtBuilder.aSoep().build();
        Gerecht veggie = GerechtBuilder.aVeggie().build();
        Gerecht dagschotel = GerechtBuilder.aDagschotel().build();
        Gerecht nok = new Gerecht("blabla", TypeGerecht.soep, 2);
        DagMenu ok = new DagMenu(LocalDate.parse("2019-03-07"),soep, dagschotel, veggie );
        DagMenu newMenu = new DagMenu(LocalDate.parse("2019-03-07"), nok, dagschotel, veggie );

        service.addDagMenu(ok);
        service.changeDagMenu(LocalDate.parse("2019-03-07"), newMenu);

        when(service.getDagmenus()).thenReturn(Arrays.asList(newMenu));
        String json = "{\"day\": \"maandag\", \"date\": \"2019-03-07\", \"soep\": {\"name\": \"blabla\", \"type\": \"soep\", \"price\": 2},\"dagschotel\": {\"name\": \"Cordon Blue\", \"type\": \"dagschotel\", \"price\": 4.20}, \"veggie\": {\"name\": \"veggie pasta\", \"type\": \"veggie\", \"price\": 4}}";
        dagMenuController.perform(patch("/dagmenu/change/2019-03-07")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].date").value("2019-03-07"))
                .andExpect(jsonPath("$[0].dayName").value("Thursday"))
                .andExpect(jsonPath("$[0].dayOfWeek").value(4))
                .andExpect(jsonPath("$[0].yearAndWeekNumber").value(201910))
                .andExpect(jsonPath("$[0].soep.name").value("blabla"))
                .andExpect(jsonPath("$[0].soep.type").value("soep"))
                .andExpect(jsonPath("$[0].soep.price").value(2))
                .andExpect(jsonPath("$[0].dagschotel.name").value("Spaghetti"))
                .andExpect(jsonPath("$[0].veggie.name").value("veggie"));

    }

    @Test
    public void givenTwoMenus_whenDeleteMenu_thenReturnJsonArray() throws Exception {
        Gerecht soep = GerechtBuilder.aSoep().build();
        Gerecht veggie = GerechtBuilder.aVeggie().build();
        Gerecht dagschotel = GerechtBuilder.aDagschotel().build();
        Gerecht nok = new Gerecht("blabla", TypeGerecht.soep, 2);
        DagMenu ok = new DagMenu(LocalDate.parse("2019-03-07"),soep, dagschotel, veggie );
        DagMenu newMenu = new DagMenu(LocalDate.parse("2019-04-07"), nok, dagschotel, veggie );

        service.addDagMenu(ok);
        service.addDagMenu(newMenu);
        service.deleteDagMenu(LocalDate.parse("2019-04-07"));

        when(service.getDagmenus()).thenReturn(Arrays.asList(ok));
        dagMenuController.perform(delete("/dagmenu/delete/2019-04-07")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().is(200));
    }
}
