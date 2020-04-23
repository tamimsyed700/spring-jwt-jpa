package ts7.spring.security.jwt.app;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ts7.spring.security.jwt.app.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUserName(String userName);
}
