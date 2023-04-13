package springtodo.springtodo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import springtodo.springtodo.embedables.Address;
import springtodo.springtodo.models.Todo;
import springtodo.springtodo.models.User;
import springtodo.springtodo.repositories.TodoRepository;
import springtodo.springtodo.repositories.UserRepository;
import springtodo.springtodo.services.interfaces.UserServiceInterface;


import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Todo createTodo(String title, Long userId, boolean completed) {
        User user = userRepository.findById(userId).get();
        return todoRepository.save(new Todo(title, completed, user));
    }

    public Todo editTodo(String userName, String title, boolean isCompleted, Long todoId){
        Todo todo = todoRepository.findById(todoId).get();
        if (!todo.getTodoUser().getUserName().equals(userName)) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not the user asigned to this Todo");
        todo.setTitle(title);
        todo.setCompleted(isCompleted);
        return todoRepository.save(todo);
    }

    public void deleteTodo(String userName, Long todoId){
        Todo todo = todoRepository.findById(todoId).get();
        if (!todo.getTodoUser().getUserName().equals(userName)) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not the user asigned to this Todo");
        todoRepository.delete(todo);
    }

    public Page<List<Object[]>> getTodos(Pageable pageable, Optional<String> searchText, Optional<String> userName){

        if (searchText.isEmpty() && userName.isEmpty()) {
            return todoRepository.findTodoDetails(pageable);
        } else if (searchText.isPresent() && userName.isEmpty()) {
            return todoRepository.findTodoDetailsTitleContaining(searchText.get(), pageable);
        } else if (searchText.isEmpty() && userName.isPresent()) {
            return todoRepository.findTodoDetailsByUsername(userName.get(), pageable);
        } else {
            return todoRepository.findTodoDetails(searchText.get(), userName.get(), pageable);
        }

    }

    public User createUser(String name, String userName, String password, Address userAddress){
        return userRepository.save(new User(name, userName, passwordEncoder.encode(password), userAddress));
    }

    public void deleteUser(String userName){
        userRepository.delete(userRepository.findByUserName(userName).get());
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUser(String userName){
        return userRepository.findByUserName(userName).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Todo> getUserTodos(String userName){
        User user = userRepository.findByUserName(userName).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<Todo> todos = user.getTodos();
        return todos;
    }

    public Todo getTodoById(Long todoId){
        return todoRepository.findById(todoId).get();
    }


}
