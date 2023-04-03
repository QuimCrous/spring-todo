package springtodo.springtodo.services.interfaces;


import springtodo.springtodo.models.Todo;

import java.util.List;

public interface UserServiceInterface {
    Todo createTodo(String title, Long userId);
    List<Todo> getTodos();
    List<Todo> getTodosByTitleContaining(String searchText);
    List<Todo> getTodosByUserName(String userName);
    Todo editTodo(String title, boolean isCompleted, Long todoId);
    void deleteTodo(Long todoId);
}
