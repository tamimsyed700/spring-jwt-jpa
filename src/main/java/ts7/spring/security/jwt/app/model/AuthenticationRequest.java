package ts7.spring.security.jwt.app.model;

import java.io.Serializable;

public class AuthenticationRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	@Override
	public String toString() {
		return "AuthenticationRequest [userName=" + userName + ", password=********************]";
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
