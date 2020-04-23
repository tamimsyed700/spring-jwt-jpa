package ts7.spring.security.jwt.app.model;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

	private String jwt;

	public AuthenticationResponse(String jwt) {
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}

}
