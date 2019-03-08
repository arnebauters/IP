package be.ucll.gerechten.controller;

import be.ucll.gerechten.model.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    private GerechtService gerechtService; //= new GerechtService();


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
    public String addFeedback(@Valid Gerecht gerecht, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getFieldErrors());
            return "addgerecht";
        } else {
            gerechtService.addGerecht(gerecht);
            model.addAttribute("gerechten", gerechtService.getAllGerechten());
            return "gerechten";
        }
    }
    @GetMapping("/gerechten/delete")
    public String getMenu(@RequestParam(name = "beschrijving") String name, Model model){
        model.addAttribute("name", name );
        return "delete";
    }
    @PostMapping("/gerechten/delete")
    public RedirectView deleteGerecht(@RequestParam Map<String,String> requestParams, Model model){
        String name = requestParams.get("beschrijving");
        String confirm = requestParams.get("confirm");
        if (confirm.equals("Nee")){
            return new RedirectView("/gerechten/change");
        }else {
            Gerecht gerecht = gerechtService.findGerechtByName(name);
            gerechtService.deleteGerecht(gerecht);
            return new RedirectView("/gerechten/change");
        }
    }

    @GetMapping("/gerechten/update")
    public String updatePagina(@RequestParam (name = "beschrijving") String name, Model model){
        Gerecht gerecht = gerechtService.findGerechtByName(name);
        model.addAttribute("gerecht", gerecht);
        return "updateConfirm";
    }

    @PostMapping("/gerechten/update")
    public String updateGerecht(@RequestParam Map<String,String> requestParams, @Valid Gerecht gerecht, BindingResult bindingResult, Model model){
        String name = requestParams.get("beschrijving");
        String update = requestParams.get("update");
        if (update.equals("Cancel")){
            return changegerecht(model);
        }
        else if (bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getFieldErrors());
            return "updateConfirm";
        }
        else {
            gerechtService.updateGerecht(name, gerecht);
            return changegerecht(model);

        }
    }
}
