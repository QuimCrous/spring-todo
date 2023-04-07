package springtodo.springtodo.services.interfaces;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import springtodo.springtodo.models.Todo;


import java.util.List;
import java.util.Optional;

public interface UserServiceInterface {
    Todo createTodo(String title, String userName, boolean completed);
    Todo editTodo(String userName, String title, boolean isCompleted, Long todoId);
    void deleteTodo(String userName, Long todoId);
    Page<List<Object[]>> getTodos(Pageable pageable, Optional<String> searchText, Optional<String> userName);
}
