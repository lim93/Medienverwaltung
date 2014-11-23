package de.yellow.medienverwaltung.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

    
    //medienverwaltung/ => Startseite (index.jsp) 
    @RequestMapping("/")
    public ModelAndView showIndex() {

        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    
    //medienverwaltung/profil => profil.jsp 
    @RequestMapping("/profil")
    public ModelAndView showProfil() {

        ModelAndView mv = new ModelAndView("profil");
        return mv;
    }
    
    //medienverwaltung/suche => suche.jsp
    @RequestMapping("/suche")
    public ModelAndView showSuche(@RequestParam(value = "suche", required = false) String suche) {

        ModelAndView mv = new ModelAndView("suche");
        mv.addObject("suche", suche);
        return mv;
    }
    
    //medienverwaltung/anlegen => anlegen.jsp 
    @RequestMapping("/anlegen")
    public ModelAndView showAnlegen() {

        ModelAndView mv = new ModelAndView("anlegen");
        return mv;
    }
}
