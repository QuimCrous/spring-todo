package springtodo.springtodo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springtodo.springtodo.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
