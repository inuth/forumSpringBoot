package be.technobel.forum.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller // dit que la classe est un controller
public class HomeController {
	

	
	// mapper la méthode à une requête
	// value = la route / le chemin
	// method : on dit qu'on attrappe que des requetes GET 
	@RequestMapping(value="/home/index", method=RequestMethod.GET)
	public String index(Model model) {
		System.out.println("JE SUIS DANS INDEX.");
		// je passe un attribut à ma vue
		// il s'appelle "prenom" et a comme valeur : "Bob"
		model.addAttribute("prenom", "Bob");
		// on retourne vers le fichier "index.html"
				// qui se trouve dans le dossiers "templates"
		return "index";
	}
	
	@PostMapping(value="")
	public String index2(String valeur) {
		System.out.println("POST " + valeur);
		return "index";
	}
	
	
}
