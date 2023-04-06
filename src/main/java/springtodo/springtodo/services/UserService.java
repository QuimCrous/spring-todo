package springtodo.springtodo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
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

    public Todo createTodo(String title, String userName, boolean completed) {
        User user = userRepository.findByUserName(userName).get();
        return todoRepository.save(new Todo(title, completed, user));
    }

    public Todo editTodo(String title, boolean isCompleted, Long todoId){
        Todo todo = todoRepository.findById(todoId).get();
        todo.setTitle(title);
        todo.setCompleted(isCompleted);
        return todoRepository.save(todo);
    }

    public void deleteTodo(Long todoId){
        Todo todo = todoRepository.findById(todoId).get();
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
}
