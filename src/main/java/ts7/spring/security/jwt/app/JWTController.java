package ts7.spring.security.jwt.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ts7.spring.security.jwt.app.model.AuthenticationRequest;
import ts7.spring.security.jwt.app.model.AuthenticationResponse;
import ts7.spring.security.jwt.app.service.MyUserDetailsService;

@RestController
public class JWTController {

	private static Logger logger = LoggerFactory.getLogger(JWTController.class);

	@Autowired
	AuthenticationManager authMgr;

	@RequestMapping("/hello")
	public String hello() {
		return "Hello World";
	}

	@Autowired
	MyUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody AuthenticationRequest request) {
		Authentication authentication;
		try {
			authentication = authMgr.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
			logger.info(" authenticateUser method " + authentication.getName());
		} catch (Exception e) {
			logger.info(e.getLocalizedMessage());
			throw new BadCredentialsException("Wrong username and password");
		}
		logger.info("Calling user Details services.");
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());
		logger.info("userDetails " + userDetails);
		String jwt = new JwtUtil().generateToken(userDetails);
		logger.info("jwt " + jwt);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));

	}
}
