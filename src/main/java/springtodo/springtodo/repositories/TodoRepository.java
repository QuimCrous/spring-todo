package springtodo.springtodo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import springtodo.springtodo.models.Todo;


import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByTitleContaining(String searchText);


    @Query("SELECT t.title, u.userName, u.userAddress.country, t.completed FROM Todo t JOIN t.todoUser u")
    Page<List<Object[]>> findTodoDetails(Pageable pageable);

    @Query("SELECT t.title, u.userName, u.userAddress.country, t.completed FROM Todo t JOIN t.todoUser u WHERE (:title IS NULL OR t.title LIKE %:title%)")
    Page<List<Object[]>> findTodoDetailsTitleContaining(@Param("title") String title, Pageable pageable);

    @Query("SELECT t.title, u.userName, u.userAddress.country, t.completed FROM Todo t JOIN t.todoUser u WHERE u.userName LIKE %:username%")
    Page<List<Object[]>> findTodoDetailsByUsername(@Param("username") String username, Pageable pageable);

    @Query("SELECT t.title, u.userName, u.userAddress.country, t.completed FROM Todo t JOIN t.todoUser u WHERE (:title IS NULL OR t.title LIKE %:title%) AND (:username IS NOT NULL AND u.userName LIKE %:username%)")
    Page<List<Object[]>> findTodoDetails(@Param("title") String title, @Param("username") String username, Pageable pageable);

}
