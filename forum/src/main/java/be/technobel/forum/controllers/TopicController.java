package be.technobel.forum.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import be.technobel.forum.dal.ForumRepository;
import be.technobel.forum.dal.TopicRepository;
import be.technobel.forum.entities.Topic;
import be.technobel.forum.forms.TopicForm;
import be.technobel.forum.mappers.TopicMapper;

@Controller
@Transactional
@RequestMapping(value="/topic")
public class TopicController {
	
	@Autowired
	ForumRepository forumRepo;
	
	@Autowired
	TopicRepository topicRepo;
	
	@GetMapping("create")
	// on doit récupérer l'id du forum dans lequel on crée le topic
	// l'id a été passée dans l'url et a comme nom "f"
	public String create(Model model, Integer f) {
		System.out.println(f);
		System.out.println("FIN");
		model.addAttribute("idForum", f);
		model.addAttribute("topic", new TopicForm());
		return "/topic/create";
	}
	
	@PostMapping("/create")
	// ATTENTION !!! le BindingResult DOIT être absolument après le paramètre
	// avec l'annotation @Valid
	public String create(@Valid @ModelAttribute("topic") TopicForm topic, BindingResult br, Integer idForum) {
		if (br.hasErrors()) {
			return "/topic/create";
		}
		System.out.println("TEST POST CREATE");
		System.out.println(idForum);
		Topic topicDB = TopicMapper.formToEntity(topic);
		topicDB.setForum(forumRepo.findById(idForum).get());
		topicRepo.save(topicDB);
		return "redirect:/forum/" + idForum + "/topics";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		Topic topic = topicRepo.findById(id).orElse(null);
		topicRepo.delete(topic);
		// une fois le topic supprimé, on souhaite retourner vers la vue 
		// qui affiche tous les topics DU forum
		// on NE va PAS mettre : return "forum/topics"  car on ne passe 
		// pas de model à la vue
		// on va plutôt rediriger vers le controller qui va gérer cette vue
		return "redirect:/forum/" + topic.getForum().getId() +"/topics";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") Integer id, Model model) {
		System.out.println("GET UPDATE");
		// on récupère le topic de la DB
		Topic topic = topicRepo.findById(id).get();
		// On le transforme en un formulaire (qui possède des annotations
		// de validation)
		TopicForm tf = TopicMapper.entityToForm(topic);
		// on envoie le formulaire à la vue
		model.addAttribute("topic", tf);
		return "/topic/update";
	}
	@PostMapping("/update")
	public String update(TopicForm topic) {
		System.out.println("POST UPDATE");
		// récupération du topic se trouvant en DB (qui n'a pas été modifié)
		Topic topicDB = topicRepo.findById(topic.getId()).get();
		// modifier ses informations sur base du formulaire qu'on a reçu	
		topicDB.setDescription(topic.getDescription());
		// je renvoie le topic modifée en DB 
		topicRepo.save(topicDB);
		return "redirect:/forum/"+topicDB.getForum().getId()+"/topics";
	}
	
	
}






