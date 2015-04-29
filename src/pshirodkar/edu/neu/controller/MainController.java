package pshirodkar.edu.neu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.google.gdata.util.AuthenticationException;

@Controller
/**
 * This controller class serves as the abstract parent controller class in the
 * application.
 * 
 * @author Prasad Shirodkar
 * 
 */
public abstract class MainController {

	@ExceptionHandler(AuthenticationException.class)
	public String handleAuthenticationException(AuthenticationException ex) {
		System.out.println(ClassUtils.getShortName(ex.getClass()));
		return "login";
	}
}
