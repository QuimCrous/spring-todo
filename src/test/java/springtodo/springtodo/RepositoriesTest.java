package springtodo.springtodo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import springtodo.springtodo.embedables.Address;
import springtodo.springtodo.models.Todo;
import springtodo.springtodo.models.User;
import springtodo.springtodo.repositories.TodoRepository;
import springtodo.springtodo.repositories.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class RepositoriesTest {

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void addUserAndFindByUserName(){
        User user1 = new User("Kevin Doe", "kevin_74", passwordEncoder.encode("123456"), new Address("street 1","city-one","Z4471","Spain"));
        userRepository.save(user1);
        Optional<User> test = userRepository.findByUserName("kevin_74");
        Assertions.assertEquals("Kevin Doe", test.get().getName());
    }

    @Test
    public void addTodoAndFindTodoDetails(){
        User user2 = new User("Albert Doe", "big_al", passwordEncoder.encode("123456"), new Address("street 1","city-one","Z4471","Spain"));
        userRepository.save(user2);
        Todo todo1 = new Todo("Create testing todos",false, user2);
        Todo todo2 = new Todo("Create testing todos 2",false, user2);
        Todo todo3 = new Todo("Create testing todos 2",false, user2);
        Todo todo4 = new Todo("Create testing todos 2",false, user2);
        todoRepository.saveAll(Arrays.asList(todo1, todo2, todo3, todo4));
        Pageable pageable = PageRequest.of(0,10);
        Page<List<Object[]>> todoDetails = todoRepository.findTodoDetails(pageable);

        Assertions.assertNotNull(todoDetails);
        Assertions.assertFalse(todoDetails.isEmpty());
        Assertions.assertEquals(10, todoDetails.getSize());



    }
}
