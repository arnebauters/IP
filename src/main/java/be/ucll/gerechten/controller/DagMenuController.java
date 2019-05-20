package be.ucll.gerechten.controller;

import be.ucll.gerechten.model.DagMenu;
import be.ucll.gerechten.model.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
public class DagMenuController {
    @Autowired
    private MyService dagMenuService;

    @GetMapping("/dagmenus")
    public List<DagMenu> getAllDagMenus(){return dagMenuService.getDagmenus();}

    @PostMapping("/dagmenu/add")
    @ResponseStatus(HttpStatus.CREATED)
    public List<DagMenu> addDagMenu(@RequestBody @Valid DagMenu dagMenu, BindingResult bindingResult){
        dagMenuService.addDagMenu(dagMenu);
        return dagMenuService.getDagmenus();
    }

    @PatchMapping("/dagmenu/change/{date}")
    public List<DagMenu> updateDagmenu(@PathVariable("date") String date, @RequestBody @Valid DagMenu dagmenu, BindingResult bindingResult){
        // we want to convert the following patterns: yyyy-MM-dd, yyyy/MM/dd, dd-MM-yyyy and dd/MM/yyyy
        // 23-09-2018, 23/09/2018, 2018-09-23 and 2018/09/23 should all convert to the same LocalDate.
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyy-MM-dd][yyyy/MM/dd][dd-MM-yyyy][dd/MM/yyyy]");
            LocalDate dateFromUrl = LocalDate.parse(date, formatter);
            dagMenuService.changeDagMenu(dateFromUrl, dagmenu);

        return dagMenuService.getDagmenus();
    }

    @DeleteMapping("/dagmenu/delete/{date}")
    public List<DagMenu> deleteDay(@PathVariable("date") String date) {
        // we want to convert the following patterns: yyyy-MM-dd, yyyy/MM/dd, dd-MM-yyyy and dd/MM/yyyy
        // 23-09-2018, 23/09/2018, 2018-09-23 and 2018/09/23 should all convert to the same LocalDate.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyy-MM-dd][yyyy/MM/dd][dd-MM-yyyy][dd/MM/yyyy]");
        LocalDate dateFromUrl = LocalDate.parse(date, formatter);

        dagMenuService.deleteDagMenu(dateFromUrl);
        return dagMenuService.getDagmenus();
    }
}
