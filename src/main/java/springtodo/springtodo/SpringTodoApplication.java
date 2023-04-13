package springtodo.springtodo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import springtodo.springtodo.embedables.Address;
import springtodo.springtodo.models.Todo;
import springtodo.springtodo.models.User;
import springtodo.springtodo.repositories.TodoRepository;
import springtodo.springtodo.repositories.UserRepository;

import java.util.Arrays;


@SpringBootApplication(scanBasePackages = "springtodo.springtodo")
public class SpringTodoApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Autowired
	TodoRepository todoRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringTodoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//We generated two users to populate the table for the tests.
		User user = new User("Jhon Doe", "jhonny_87", passwordEncoder.encode("123456"), new Address("Street One", "City One", "Z0845", "Country"));
		userRepository.save(user);
		User user1 = new User("Mike Doe", "miky_doe", passwordEncoder.encode("123456"), new Address("Street One", "City One", "Z0845", "Spain"));
		userRepository.save(user1);

		//We generated some Todos to populate the table for the tests.
		Todo todo1 = new Todo("Make test to controllers", false, user);
		Todo todo2 = new Todo("Make test to controllers", false, user);
		Todo todo3 = new Todo("Make test to controllers", true, user);
		Todo todo4 = new Todo("Make test to controllers", false, user);
		Todo todo5 = new Todo("Make test to controllers", false, user);
		Todo todo6 = new Todo("Make test to controllers", true, user);
		Todo todo7 = new Todo("Make test to controllers", false, user);
		Todo todo8 = new Todo("Make test to controllers", false, user);
		Todo todo9 = new Todo("Make test to controllers", false, user);
		Todo todo10 = new Todo("Do test to controllers", false, user);
		todoRepository.saveAll(Arrays.asList(todo1,todo2,todo3,todo4,todo5,todo6,todo7,todo8,todo9,todo10));

		Todo mikeTodo1 = new Todo("Do the laundry", false, user1);
		Todo mikeTodo2 = new Todo("Clean the living room", false, user1);
		Todo mikeTodo3 = new Todo("Buy eggs and bread", false, user1);
		Todo mikeTodo4 = new Todo("Do a list of Todos", true, user1);
		Todo mikeTodo5 = new Todo("Go for a walk", false, user1);
		Todo mikeTodo6 = new Todo("Ask Mary to go to the party", false, user1);
		Todo mikeTodo7 = new Todo("Buy some chips and beers", false, user1);
		Todo mikeTodo8 = new Todo("Another test todo", false, user1);
		Todo mikeTodo9 = new Todo("Make test to controllers", false, user1);
		Todo mikeTodo10 = new Todo("Do test to controllers", false, user1);
		todoRepository.saveAll(Arrays.asList(mikeTodo1,mikeTodo2,mikeTodo3,mikeTodo4,mikeTodo5,mikeTodo6,mikeTodo7,mikeTodo8,mikeTodo9,mikeTodo10));
	}
}
