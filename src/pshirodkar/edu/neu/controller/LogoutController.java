package pshirodkar.edu.neu.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The purpose of this servlet is to invalidate the user session.
 * 
 * @author Prasad
 * 
 */
@Controller
public class LogoutController {

	@RequestMapping(value = "/Logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "forward:login.html";
	}
}