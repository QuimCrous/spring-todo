package springtodo.springtodo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springtodo.springtodo.models.Todo;
import springtodo.springtodo.models.User;
import springtodo.springtodo.repositories.TodoRepository;
import springtodo.springtodo.repositories.UserRepository;
import springtodo.springtodo.services.interfaces.UserServiceInterface;

import java.util.List;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TodoRepository todoRepository;

    public Todo createTodo(String title, Long userId) {
        User user = userRepository.findById(userId).get();
        return todoRepository.save(new Todo(title, false, user));
    }

    public List<Todo> getTodos(){
        return todoRepository.findAll();
    }

    public List<Todo> getTodosByTitleContaining(String searchText){
        return todoRepository.findByTitleContaining(searchText);
    }

    public List<Todo> getTodosByUserName(String userName){
        User user = userRepository.findByUserName(userName).get();
        return user.getTodos();
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

}
