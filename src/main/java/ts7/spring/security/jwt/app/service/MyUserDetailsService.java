package ts7.spring.security.jwt.app.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ts7.spring.security.jwt.app.UserRepository;
import ts7.spring.security.jwt.app.model.MyUserDetails;
import ts7.spring.security.jwt.app.model.User;

@Service
public class MyUserDetailsService implements UserDetailsService {

	private static Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		logger.info("userName " + userName);
		Optional<User> user = userRepository.findByUserName(userName);
		logger.info("User " + user);
		user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
		MyUserDetails userDetails = user.map(MyUserDetails::new).get();
		logger.info("MyUserDetails " + userDetails);
		// return new User("allah", "pass", new ArrayList<>());
		return userDetails;
	}
}
