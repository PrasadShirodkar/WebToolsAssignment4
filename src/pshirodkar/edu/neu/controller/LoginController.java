package pshirodkar.edu.neu.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pshirodkar.edu.neu.constants.ReferenceConstants;
import pshirodkar.edu.neu.model.LoginBean;
import pshirodkar.edu.neu.validator.LoginValidator;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.util.AuthenticationException;

/**
 * This controller serves to authenticate the user credentials.
 * 
 * @author Prasad
 *
 */
@Controller
public class LoginController extends MainController {

	/*@Autowired
	private Validator validator;

	public void setValidator(Validator validator) {
		this.validator = validator;
	}*/

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("command") LoginBean loginBean,
			BindingResult result, HttpSession session) throws AuthenticationException {

		new LoginValidator().validate(loginBean, result);
		if (result.hasErrors()) {
			return "login";
		}

		CalendarService calendarService = new CalendarService("google-calendar");
		
		calendarService.setUserCredentials(loginBean.getUserName(),
				loginBean.getPassword());
		
		// Set user credentials in session
		session.setAttribute(ReferenceConstants.USER_INFO, loginBean);
		
		// Set the instance of Google CalendarService in session
		session.setAttribute(ReferenceConstants.CALENDAR_SERVICE_INFO, calendarService);
		
		return "forward:AvailableCalendars.html";
	}

	@RequestMapping("/displayLogin")
	public ModelAndView displayLogin() {

		return new ModelAndView("login", "command", new LoginBean());
	}
}