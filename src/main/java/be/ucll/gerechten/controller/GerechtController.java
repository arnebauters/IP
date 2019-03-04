package be.ucll.gerechten.controller;

import be.ucll.gerechten.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.util.List;

@Controller
public class GerechtController implements WebMvcConfigurer {
    @Autowired
    private GerechtService gerechtService; //= new GerechtService();


    @GetMapping("/home")
    public String home() {
        return "index";
    }

    @GetMapping("/gerechten")
    public String gerecht(Model model) {
        model.addAttribute("gerechten", gerechtService.getAllGerechten());
        return "gerechten";
    }
/*
    @GetMapping("/feedback/add")
    public String addFeedbackForm() {
        return "addFeedback";
    }

    @PostMapping("/feedback/add")
    public String addFeedback(@Valid Feedback feedback, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getFieldErrors());
            return "addFeedback";
        } else {
            feedbackService.addFeedback(feedback);
            model.addAttribute("feedbacks", feedbackService.getAllFeedbacks());
            return "feedbacks";
        }
    }

    // search for a feedback by ID
    // you can do a similar search by name (or even feedback)
    @GetMapping("/feedback/searchbyid/{id}")
    public String getFeedbackById(@PathVariable("id") int id, Model model) {
        Feedback feedback = feedbackService.findFeedbackById(id);
        model.addAttribute("feedback", feedback);
        return "feedbackById";
    }

    // give the correct error back with this handler, 400 instead of 500
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Requested ID not found!")
    @ExceptionHandler(value = IllegalArgumentException.class)
    public void badIdExecptionHandler(){
        // really nothing to do here
    }*/

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
    @GetMapping("gerechten/delete")
    public String getMenu(@RequestParam(name = "beschrijving") String typeGerecht, Model model){
        model.addAttribute("typegerecht", typeGerecht);
        return "delete";
    }
}
