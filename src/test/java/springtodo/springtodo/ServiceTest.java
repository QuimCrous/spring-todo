package springtodo.springtodo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;
import springtodo.springtodo.embedables.Address;
import springtodo.springtodo.models.Todo;
import springtodo.springtodo.models.User;
import springtodo.springtodo.repositories.TodoRepository;
import springtodo.springtodo.repositories.UserRepository;
import springtodo.springtodo.services.UserService;

import java.util.List;

@SpringBootTest
public class ServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Test
    @DisplayName("Create Todo works Ok")
    void createTodoWorksOk(){
        Todo todo = userService.createTodo("Just another test", 1L, false);
        Todo todo1 = todoRepository.findByTitleContaining("Just another test").get(0);
        Assertions.assertTrue(todo1.getTitle().equals("Just another test"));

    }

    @Test
    @DisplayName("Edit Todo works Ok")
    void editTodoWorksOk(){
        Todo todo = userService.createTodo("Let's make more test", 1L, false);
        Todo todo2 = userService.editTodo("jhonny_87", "La la land", false,23L);
        Assertions.assertFalse(todo2.getTitle().contains("Let's make more test"));
        Assertions.assertTrue(todo2.getTodoUser().getUserId().equals(todo.getTodoUser().getUserId()));
    }

    @Test
    @DisplayName("Delete Todo works Ok")
    void deleteTodoWorksOk(){
        Todo todo = userService.createTodo("Let's make more test Mario", 1L, false);
        userService.deleteTodo("jhonny_87", todoRepository.findByTitleContaining("Mario").get(0).getId());
        Assertions.assertTrue(todoRepository.findByTitleContaining("Mario").isEmpty());

    }

    @Test
    @DisplayName("Create and Delete User works Ok")
    void createAndDeleteUserWorksOk(){
        userService.createUser("Jean Doe", "Jeanny_90", "123456", new Address("Street One", "City One", "Z0845", "Country"));
        Assertions.assertTrue(userRepository.findByUserName("Jeanny_90").isPresent());
        userService.deleteUser("Jeanny_90");
        Assertions.assertFalse(userRepository.findByUserName("Jeanny_90").isPresent());
    }

    @Test
    @DisplayName("Edit Todo throws Exception wrong user")
    void editTodoThrowsException(){
        Assertions.assertThrows(ResponseStatusException.class, ()->{
            userService.editTodo("Jeanny_90", "La la land", false,1L);
        });
    }

    @Test
    @DisplayName("Delete Todo throws Exception wrong user")
    void deleteTodoThrowsException(){
        Assertions.assertThrows(ResponseStatusException.class, ()->{
            userService.deleteTodo("Jeanny_90",1L);
        });
    }

    @Test
    @DisplayName("Get all users works Ok")
    void getAllUsersWorksOk(){
        List<User> userList = userService.getAllUsers();
        Assertions.assertEquals(userList.size(), 2);
        Assertions.assertFalse(userList.isEmpty());
    }

    @Test
    @DisplayName("Get todo by id works Ok")
    void getTodoByIdWorksOk(){
        Todo todo = userService.getTodoById(1L);
        Assertions.assertEquals(todo.getTitle(), "Make test to controllers");
    }
}
