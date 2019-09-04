 package be.technobel.forum.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import be.technobel.forum.entities.User;

@Controller
//toutes les méthodes vont commencer par "/user"
@RequestMapping("/user") 
public class UserController {
	// méthode qui a pour but de simuler des users en DB
	private List<User> users;
	private List<User> getUsers(){
		if (users == null) {
			users = new ArrayList<>();
			users.add(new User(1, "bob", "1234", LocalDate.of(2000, 11, 15)));
			users.add(new User(2, "admin", "admin", LocalDate.of(1950, 3, 10)));
			users.add(new User(3, "toto", "1234", LocalDate.of(2010, 12, 5)));
		}
		return users;
	}
	
	@GetMapping("/connection")
	public String connection() {
		User u = new User(1,"aaa", "bbb", LocalDate.now());
		getUsers().add(u);
		System.out.println(users.size());
		return "connection";
	}
	
	@RequestMapping(value="/connection", method=RequestMethod.POST)
	public String connection(String pseudo, 
			String password, /*Model model,*/ HttpServletRequest request) 
	{
		//model.addAttribute("pseudo", pseudo);	
		//System.out.println(pseudo + " " + password);
		
		// Récupération de la session via le paramètre d'entrée
		// HttpServletRequest
		HttpSession session = request.getSession();
		// on va mettre en session l'utilisateur connecté	
		session.setAttribute("userConnected", getUsers().stream()
				.filter(u -> u.getLogin().equals(pseudo) && u.getPassword().equals(password))
				.findFirst() // Optional<User>
				.orElse(null)
				);
		return "index";
	}
	@GetMapping("/inscription")
	public String inscription() {
		return "inscription";
	}
	
	@PostMapping("/inscription")
	public String inscription(
			String pseudo, String password, 
			String birthDate
			)
	{
		
		LocalDate localDate = LocalDate.parse(birthDate);
		System.out.println(pseudo + " " + password + " " + localDate);
		return "inscription";
	}
}
