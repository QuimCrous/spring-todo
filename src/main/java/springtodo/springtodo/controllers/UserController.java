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
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserServiceInterface userServiceInterface;

    @PostMapping("/todos/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Todo createTodo(@RequestBody TodoDTO todoDTO){
        return userServiceInterface.createTodo(todoDTO.getTitle(), todoDTO.getUserName(), todoDTO.isCompleted());
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
    public ResponseEntity<Page<List<Object[]>>> getTodos(@RequestParam(required = false) String searchText, @RequestParam(required = false) String userName, Pageable pageable){
        Optional<String> optionalText = Optional.ofNullable(searchText);
        Optional<String> optionalUserName = Optional.ofNullable(userName);
        Page<List<Object[]>> todoPage = userServiceInterface.getTodos(pageable, optionalText, optionalUserName);
        return ResponseEntity.ok(todoPage);
    }




}
