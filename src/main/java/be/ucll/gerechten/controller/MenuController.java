package be.ucll.gerechten.controller;

import be.ucll.gerechten.model.DagMenu;
import be.ucll.gerechten.model.Gerecht;
import be.ucll.gerechten.model.MyService;
import be.ucll.gerechten.model.WeekMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MenuController {
    @Autowired
    private MyService weekMenuService;

    @GetMapping("/weekmenu")
    public List<WeekMenu> getMenus() {
        return weekMenuService.getWeekMenus();
    }

    @GetMapping("/weekmenu/{id}")
    public WeekMenu getWeekMenuById(@PathVariable("id") int id) {
        return weekMenuService.getWeekMenuById(id).orElseThrow(IllegalArgumentException::new);
    }

}
