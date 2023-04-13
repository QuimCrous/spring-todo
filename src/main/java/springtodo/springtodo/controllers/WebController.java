package springtodo.springtodo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springtodo.springtodo.DTOs.TodoDTO;
import springtodo.springtodo.models.Todo;
import springtodo.springtodo.models.User;
import springtodo.springtodo.services.UserService;

import java.util.List;
import java.util.Optional;

@Controller
public class WebController {

    @Autowired
    UserService userService;


    @GetMapping("/")
    public String getAllTodos(Model model, @RequestParam(required = false) Integer page, @RequestParam(required = false) String searchText, @RequestParam(required = false) String userName){
        Optional<String> optionalText = Optional.ofNullable(searchText);
        Optional<String> optionalUserName = Optional.ofNullable(userName);
        Page<List<Object[]>> todoPage;
        if (page != null){
            todoPage = userService.getTodos(PageRequest.of(page,10), optionalText, optionalUserName);
        } else {
            todoPage = userService.getTodos(PageRequest.of(0,10), optionalText, optionalUserName);
        }
        model.addAttribute("todos", todoPage);
        return "gettodos";
    }

    @GetMapping("/createtodo")
    public String showCreateTodoForm(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("todoDTO", new TodoDTO());
        return "create-todo";
    }

    @PostMapping("/createtodo/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String createTodo(@ModelAttribute TodoDTO todoDTO){
        userService.createTodo(todoDTO.getTitle(), todoDTO.getUserId(), todoDTO.isCompleted());
        return "created";
    }

    @GetMapping("/mytodos")
    public String getMyTodos(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        List<Todo> todos = userService.getUserTodos(userDetails.getUsername());

        model.addAttribute("todos", todos);

        return "my-todos";
    }

    @GetMapping("/mytodos/{id}/edit")
    public String showEditTodoForm(@PathVariable("id") Long id, Model model) {
        Todo todo = userService.getTodoById(id);
        TodoDTO todoDTO = new TodoDTO();

        model.addAttribute("todo", todo);
        model.addAttribute("todoDTO", todoDTO);

        return "edit-todo";
    }

    @PostMapping("/mytodos/{id}/update")
    public String updateTodo(@PathVariable("id") Long id, @ModelAttribute("todo") TodoDTO todoDTO, @AuthenticationPrincipal UserDetails userDetails) {
        Todo updatedTodo = userService.editTodo(userDetails.getUsername(),todoDTO.getTitle(),todoDTO.isCompleted(), id);

        return "redirect:/mytodos";
    }

    @GetMapping("/mytodos/{id}/delete")
    public String deleteTodo(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetails userDetails) {
        userService.deleteTodo(userDetails.getUsername(), id);

        return "redirect:/mytodos";
    }
}
