package be.technobel.forum.controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import be.technobel.forum.dal.ForumRepository;
import be.technobel.forum.entities.Forum;

@Controller
@RequestMapping("/forum")
// il va gérer les transactions vers la DB
@Transactional
public class ForumController {
	
	// Spring va gérer tout le LIFE CYCLE de l'objet
	// => création, accès, ...
	@PersistenceContext 
	EntityManager em;
	@Autowired
	ForumRepository forumRepo;
	// private List<Forum> forums;
//	private Integer id = 1;
//	private List<Forum> getForums(){
//		if(forums == null)
//		{
//			// JPQL !!! (on fait des requêtes par rapport aux entités
//			// et non par rapport aux tables
//			// donc pas oublier de mettre Forum avec un "F"
//			Query query = em.createQuery("SELECT f from Forum f");
//			forums = query.getResultList();
////			forums = new ArrayList<>();
////			forums.add(new Forum(id++,"La sauce Ketchup"));
////			forums.add(new Forum(id++,"La balle au prisonnier"));
////			forums.add(new Forum(id++,"Fromage de chêvre"));
//		}
//		return forums;
//	}
	
	private List<Forum> getForums(){
			TypedQuery<Forum> query = em.createQuery("SELECT f FROM Forum f", Forum.class);
			List<Forum> forums = query.getResultList();
			return forums;
	}
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String index(Model model) {
		em.find(Forum.class, 1);
		model.addAttribute("forums", getForums());
		return "forum/forums";
		// par défaut, il ajoute "/templates" avant et ".html" apres
		// le String renvoyé
		//return "/templates/" + "..." + ".html";
	}
	
	@GetMapping("create")
	public String create() {
		return "forum/create";
	}
	
	// METHODE 1
	
//	@PostMapping("create")
//	public String create(String title, Model model) {
//		// But de cette méthode : créer un nouveau forum
//		Forum f = new Forum(id++, title);
//		getForums().add(f);
//		return "/forum";
//		
//	}
	
	 // METHODE 2
	@PostMapping("create")
	public RedirectView create(String title, Model model) {
		// But de cette méthode : créer un nouveau forum
		Forum f = new Forum(title);
		// on insère
		em.persist(f);
		//em.flush();
		// le model sera regénéré donc : perte des attributs qu'on
		// aurait ajouté ici
		return new RedirectView("/forum");
		
	}
	
	 // METHODE 3 (ou l'on redirige MAIS en gardant le meme model)
	
//	@PostMapping("create")
//	public ModelAndView create(String title, ModelMap model) {
//		// But de cette méthode : créer un nouveau forum
//		Forum f = new Forum(id++, title);
//		getForums().add(f);
//		model.addAttribute("message", "ok");
//		// le model sera regénéré donc : perte des attributs qu'on
//		// aurait ajouté ici
//		
//		return new ModelAndView("redirect:", model);
//		
//	}
	
	// le mapping de cette méthode est "/details/..." peu importe
	// la valeur mise après le /details
	// elle va être convertie en Integer car dans les paramètres
	// de la méthode, on a mis : @PathParam("monAbricot") Integer id
	// On lui dit quel paramètre on veut et dans quelle variable
	// le mettre
	@GetMapping("/details/{monAbricot}")
	public String details(@PathVariable("monAbricot") Integer id, Model model) {
		// afficher les détails d'un forum
		// lequel ??? celui qui correspond à l'id
		System.out.println(id);
		Forum f = getForums()
				.stream()
				.filter(x-> x.getId() == id)
				.findFirst() // Optional<Forum>
				.orElse(null);
		// si on a trouvé aucun forum
		// on redirige vers la liste des forums
		if (f == null) {
			return "redirect:/forum";
		}
		model.addAttribute("forum", f);
		return "forum/details";
	}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id)
	{
		// retirer l'élément de la liste
		Forum fo = getForums().stream().filter(f -> f.getId() == id).findFirst().orElse(null);
		if (fo != null) {
			em.remove(fo);
		}
		
		return "redirect:/forum";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") Integer id, Model model)
	{
		Forum f = getForums()
				.stream()
				.filter(x-> x.getId() == id)
				.findFirst() // Optional<Forum>
				.orElse(null);
		// si on a trouvé aucun forum
		// on redirige vers la liste des forums
		if (f == null) {
			return "redirect:/forum";
		}
		model.addAttribute("forum", f);
		return "forum/update";
	}
	
	@PostMapping("/update/{id}")
	public String update(@PathVariable("id") Integer id, String title) {
		// Savoir qui on a voulu modifier
		Forum f = getForums()
				.stream()
				.filter(x-> x.getId() == id)
				.findFirst() // Optional<Forum>
				.orElse(null);
		// Et du coup le modifier
		if (f != null) {
			f.setTitle(title);
		}		
		return "redirect:/forum";
		
	}
	
	// méthode qui va afficher une page où seront mis
	// tous les topics d'un forum
	@RequestMapping(value="/{id}/topics", method=RequestMethod.GET)
	public String index(Model model, @PathVariable("id") Integer id) {
		// permet de récupérer un élément grace à son id
		Forum f = forumRepo.findById(id).orElse(null);
		if (f != null) {
			model.addAttribute("forum", f);	
			return "/forum/topics";
		}
		return "redirect:/forum";	
	}
}
