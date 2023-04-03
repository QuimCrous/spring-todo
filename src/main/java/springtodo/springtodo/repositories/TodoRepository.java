package springtodo.springtodo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springtodo.springtodo.models.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
