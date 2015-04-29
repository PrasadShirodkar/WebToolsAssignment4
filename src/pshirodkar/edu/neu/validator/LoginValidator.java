package pshirodkar.edu.neu.validator;

import org.apache.commons.validator.EmailValidator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pshirodkar.edu.neu.model.LoginBean;

/**
 * This validator class validates whether the username and password are empty or
 * not.
 * 
 * @author Prasad
 * 
 */
public class LoginValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return LoginBean.class.equals(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {

		validateUserName(obj, errors);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
				"validation.password.required");
	}

	private void validateUserName(Object obj, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName",
				"validation.userName.required");
		
		if (obj instanceof LoginBean) {
			
			LoginBean loginBean = (LoginBean)obj;
			
			EmailValidator emailValidator = EmailValidator.getInstance();
			if (!emailValidator.isValid(loginBean.getUserName())) {
				errors.rejectValue("userName", "validation.userName.invalid");
			}
		}
	}
}