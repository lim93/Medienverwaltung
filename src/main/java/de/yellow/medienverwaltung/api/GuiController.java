package de.yellow.medienverwaltung.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GuiController {

	// medienverwaltung/ => Startseite (index.jsp)
	@RequestMapping("/")
	public ModelAndView showIndex() {

		ModelAndView mv = new ModelAndView("index");
		return mv;
	}

	// medienverwaltung/profil => profil.jsp
	@RequestMapping("/profil")
	public ModelAndView showProfil() {

		ModelAndView mv = new ModelAndView("profil");
		return mv;
	}

	// medienverwaltung/master => master.jsp
	@RequestMapping("/master")
	public ModelAndView showMaster(
			@RequestParam(value = "masterId", required = true) int masterId) {

		ModelAndView mv = new ModelAndView("master");
		mv.addObject("masterId", masterId);
		return mv;
	}

	// medienverwaltung/version => version.jsp
	@RequestMapping("/version")
	public ModelAndView showVersion(
			@RequestParam(value = "versionId", required = true) int versionId,
			@RequestParam(value = "masterId", required = true) int masterId) {

		ModelAndView mv = new ModelAndView("version");
		mv.addObject("versionId", versionId);
		mv.addObject("masterId", masterId);
		return mv;
	}

	// medienverwaltung/suche => suche.jsp
	@RequestMapping("/suche")
	public ModelAndView showSuche(
			@RequestParam(value = "suche", required = false) String suche) {

		ModelAndView mv = new ModelAndView("suche");
		mv.addObject("suche", suche);
		return mv;
	}

	// medienverwaltung/anlegen_master => anlegen_master.jsp
	@RequestMapping("/anlegen_master")
	public ModelAndView showAnlegenMaster() {

		ModelAndView mv = new ModelAndView("anlegen_master");
		return mv;
	}

	// medienverwaltung/anlegen_version => anlegen_verion.jsp
	@RequestMapping("/anlegen_version")
	public ModelAndView showAnlegenVersion(
			@RequestParam(value = "masterId", required = true) int masterId) {

		ModelAndView mv = new ModelAndView("anlegen_version");
		mv.addObject("masterId", masterId);
		return mv;
	}

}
