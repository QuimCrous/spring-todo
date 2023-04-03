package springtodo.springtodo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springtodo.springtodo.DTOs.TodoDTO;
import springtodo.springtodo.models.Todo;
import springtodo.springtodo.services.interfaces.UserServiceInterface;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserServiceInterface userServiceInterface;

    @PostMapping("/todos/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Todo createTodo(@RequestParam Long id, @RequestBody String title){
        return userServiceInterface.createTodo(title, id);
    }

    @PutMapping("/todos/edit")
    @ResponseStatus(HttpStatus.OK)
    public Todo editTodo(@RequestParam Long id, @RequestBody TodoDTO todoDTO){
        return userServiceInterface.editTodo(todoDTO.getTitle(), todoDTO.isCompleted(), id);
    }

    @DeleteMapping("/todos/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTodo(@RequestBody Long todoId){
        userServiceInterface.deleteTodo(todoId);
    }


    @GetMapping("/todos/get-todos")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<List<Object[]>>> getTodosThree(Pageable pageable){
        Page<List<Object[]>> todoPage = userServiceInterface.getTodos(pageable);
        return ResponseEntity.ok(todoPage);
    }

    @GetMapping("/todos/get-title")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<List<Object[]>>> getTodosByTitleContaining(@RequestBody String title, Pageable pageable){
        Page<List<Object[]>> todoPage = userServiceInterface.getTodosByTitleContaining(title, pageable);
        return ResponseEntity.ok(todoPage);
    }

    @GetMapping("/todos/get-username")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<List<Object[]>>> getTodosByUserName(@RequestBody String userName, Pageable pageable){
        Page<List<Object[]>> todoPage = userServiceInterface.getTodosByUserName(userName, pageable);
        return ResponseEntity.ok(todoPage);
    }


}
