package be.technobel.forum.utils;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import be.technobel.forum.entities.User;

public class SessionManager {
	// TODO : trouver un moyen de récupérer directement le user
	// en session et non pas seulement son nom
	
	public static String userConnected = "userConnected";
	
}
