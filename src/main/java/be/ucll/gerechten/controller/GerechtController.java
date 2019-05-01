package be.ucll.gerechten.controller;

import be.ucll.gerechten.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class GerechtController implements WebMvcConfigurer {
    @Autowired
    private MyService gerechtService;


    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("gerechten", gerechtService.getAllGerechten());
        return "gerechten";
    }

    @GetMapping("/gerechten")
    public String gerecht(Model model) {
        model.addAttribute("gerechten", gerechtService.getAllGerechten());
        return "gerechten";
    }

    @GetMapping("/gerechten/change")
    public String changegerecht(Model model) {
        model.addAttribute("gerechten", gerechtService.getAllGerechten());
        return "changegerechten";
    }

    @GetMapping("/gerechten/add")
    public String addGerechtForm(Model model) {
        model.addAttribute("types", TypeGerecht.values());
        return "addgerecht";
    }

    @PostMapping("/gerechten/add")
    public String addGerecht(@Valid Gerecht gerecht, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getFieldErrors());
            model.addAttribute("types", TypeGerecht.values());
            return "addgerecht";
        } else {
            gerechtService.addGerecht(gerecht);
            model.addAttribute("gerechten", gerechtService.getAllGerechten());
            return "gerechten";
        }
    }

    //delete gerecht

    @GetMapping(value = "/gerechten/delete}", params = {"name"})
    public String deleteGerecht(@RequestParam(name = "name") String name, Model model){
        Gerecht gerecht = gerechtService.findGerechtByName(name);
        model.addAttribute("gerecht", gerecht );
        return "confirmDelete";
    }
    @GetMapping(value = "/gerechten/delete", params = {"name"})
    public RedirectView deleteGerechtConfirm(@RequestParam(name = "name") String name, Model model){
        Gerecht gerecht = gerechtService.findGerechtByName(name);
        gerechtService.deleteGerecht(gerecht);
        return new RedirectView("/gerechten");
    }


    //update gerecht

    @GetMapping(value = "/gerechten/update", params = {"name"})
    public String updatePagina(@RequestParam (name = "name") String name, Model model){
        Gerecht gerecht = gerechtService.findGerechtByName(name);
        model.addAttribute("gerecht", gerecht);
        model.addAttribute("types", TypeGerecht.values());
        return "updateConfirm";
    }

    @PostMapping("/gerechten/update")
    @ResponseStatus(HttpStatus.OK)
    public String updateGerecht(@Valid Gerecht gerecht, Model model, BindingResult bindingResult){

         if (bindingResult.hasErrors()){
             model.addAttribute("errors", bindingResult.getFieldErrors());
             model.addAttribute("types", TypeGerecht.values());
            return "updateConfirm";
        }
        else {
            gerechtService.updateGerecht(gerecht);
            model.addAttribute("gerechten", gerechtService.getAllGerechten());
            return "gerechten";

        }
    }
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Requested ID not found!")
    @ExceptionHandler(value = IllegalArgumentException.class)
    public void badIdExecptionHandler() {
        // really nothing to do here
    }
}
