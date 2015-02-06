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

	// medienverwaltung/login => login.jsp
	@RequestMapping("/login")
	public ModelAndView showLogin(
			@RequestParam(value = "error", required = false) Integer error) {

		ModelAndView mv = new ModelAndView("login");
		mv.addObject("error", error);

		return mv;
	}

	// medienverwaltung/loginSuccessful => loginSuccessful.jsp
	@RequestMapping("/loginSuccessful")
	public ModelAndView loginSuccessful() {

		ModelAndView mv = new ModelAndView("loginSuccessful");

		return mv;
	}

	// medienverwaltung/logout => logout.jsp
	@RequestMapping("/logout")
	public ModelAndView logout() {

		ModelAndView mv = new ModelAndView("logout");

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

	// medienverwaltung/artist => artist.jsp
	@RequestMapping("/artist")
	public ModelAndView showArtist(
			@RequestParam(value = "artistId", required = true) int artistId) {

		ModelAndView mv = new ModelAndView("artist");
		mv.addObject("artistId", artistId);
		return mv;
	}

	// medienverwaltung/label => label.jsp
	@RequestMapping("/label")
	public ModelAndView showLabel(
			@RequestParam(value = "labelId", required = true) int labelId) {

		ModelAndView mv = new ModelAndView("label");
		mv.addObject("labelId", labelId);
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
