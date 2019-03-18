package be.ucll.gerechten.controller;

import be.ucll.gerechten.model.DagMenu;
import be.ucll.gerechten.model.WeekMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MenuController {
    @Autowired
    private WeekMenu weekMenu;

    @GetMapping("/weekmenu")
    public List<DagMenu> getMenus() {
        return weekMenu.getAllMenus();
    }


    @PostMapping("/dagmenu/add")
    @ResponseStatus(HttpStatus.CREATED)
    public List<DagMenu> addDagMenu(@RequestBody @Valid DagMenu dagMenu){
        weekMenu.addMenu(dagMenu);
       return weekMenu.getAllMenus();
    }

    @PutMapping("/dagmenu/change/{date}")
    public List<DagMenu> updateDagmenu(@PathVariable("date") String date, @RequestBody @Valid DagMenu dagmenu){
        weekMenu.updateDagmenu(date, dagmenu);
        return weekMenu.getAllMenus();
    }
}
