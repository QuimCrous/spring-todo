package springtodo.springtodo.services.interfaces;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import springtodo.springtodo.models.Todo;


import java.util.List;

public interface UserServiceInterface {
    Todo createTodo(String title, Long userId);
    Todo editTodo(String title, boolean isCompleted, Long todoId);
    void deleteTodo(Long todoId);
    Page<List<Object[]>> getTodos(Pageable pageable);
    Page<List<Object[]>> getTodosByTitleContaining(String searchText, Pageable pageable);
    Page<List<Object[]>> getTodosByUserName(String userName, Pageable pageable);
}
