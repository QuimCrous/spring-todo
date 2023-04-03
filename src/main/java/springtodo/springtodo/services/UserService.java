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


    public Page<List<Object[]>> getTodosByTitleContaining(String searchText, Pageable pageable){
        return todoRepository.findTodoDetailsTitleContaining(searchText, pageable);
    }

    public Page<List<Object[]>> getTodosByUserName(String userName, Pageable pageable){
        return todoRepository.findTodoDetailsByUsername(userName, pageable);
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



    public Page<List<Object[]>> getTodos(Pageable pageable){

        return todoRepository.findTodoDetails(pageable);
    }
}
