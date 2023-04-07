package springtodo.springtodo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import springtodo.springtodo.embedables.Address;
import springtodo.springtodo.models.User;
import springtodo.springtodo.repositories.UserRepository;


@SpringBootApplication(scanBasePackages = "springtodo.springtodo")
public class SpringTodoApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringTodoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User("Quim", "quim_dev", passwordEncoder.encode("123456"), new Address("calle jolin", "BCN", "Z0845", "Spain"));
		userRepository.save(user);
	}
}
