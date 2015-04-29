package pshirodkar.edu.neu.model;

/**
 * This domain class stores the credentials of the authenticated user.
 * 
 * @author Prasad
 *
 */
public class LoginBean {
	
	private String userName;
	
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}