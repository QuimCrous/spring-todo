package springtodo.springtodo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springtodo.springtodo.models.Todo;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByTitleContaining(String searchText);
    //List<Todo> findByUserUserName(String username);

}
