package springtodo.springtodo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import springtodo.springtodo.DTOs.TodoDTO;
import springtodo.springtodo.DTOs.UserDTO;
import springtodo.springtodo.models.Todo;
import springtodo.springtodo.models.User;
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
        return userServiceInterface.createTodo(todoDTO.getTitle(), todoDTO.getUserId(), todoDTO.isCompleted());
    }

    @PutMapping("/todos/edit")
    @ResponseStatus(HttpStatus.OK)
    public Todo editTodo(@AuthenticationPrincipal UserDetails userDetails, @RequestParam Long id, @RequestBody TodoDTO todoDTO){
        return userServiceInterface.editTodo(userDetails.getUsername(), todoDTO.getTitle(), todoDTO.isCompleted(), id);
    }

    @DeleteMapping("/todos/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTodo(@AuthenticationPrincipal UserDetails userDetails, @RequestParam Long todoId){
        userServiceInterface.deleteTodo(userDetails.getUsername(), todoId);
    }


    @GetMapping("/todos/get-todos")
    @ResponseStatus(HttpStatus.OK)
    public Page<List<Object[]>> getTodos(@RequestParam(required = false) String searchText, @RequestParam(required = false) String userName, Pageable pageable){
        Optional<String> optionalText = Optional.ofNullable(searchText);
        Optional<String> optionalUserName = Optional.ofNullable(userName);
        Page<List<Object[]>> todoPage = userServiceInterface.getTodos(pageable, optionalText, optionalUserName);
        return todoPage;
    }

    @PostMapping("/user/create")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody UserDTO userDTO){
        return userServiceInterface.createUser(userDTO.getName(), userDTO.getUserName(), userDTO.getPassword(), userDTO.getAddress());
    }

    @DeleteMapping("/user/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@AuthenticationPrincipal UserDetails userDetails){
        userServiceInterface.deleteUser(userDetails.getUsername());
    }


}
