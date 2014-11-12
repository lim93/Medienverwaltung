package de.yellow.medienverwaltung.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {
    String message = "Welcome to Spring MVC!";

    // Unter "medienverwaltung/" geben wir die index.jsp zurück
    @RequestMapping("/")
    public ModelAndView showIndex() {
        System.out.println("index");

        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    // und unter "medienverwaltung/hello die helloworld.jsp
    @RequestMapping("/hello")
    public ModelAndView showMessage(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        System.out.println("/hello");

        ModelAndView mv = new ModelAndView("helloworld");
        mv.addObject("message", message);
        mv.addObject("name", name);
        return mv;
    }
}
