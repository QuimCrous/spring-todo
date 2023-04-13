package springtodo.springtodo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import springtodo.springtodo.models.Todo;
import springtodo.springtodo.repositories.TodoRepository;
import springtodo.springtodo.services.UserService;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
public class WebControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Autowired
    TodoRepository todoRepository;

    private MockMvc mockMvc;



    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    @DisplayName("Get all todos works Ok")
    public void testGetAllTodos() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/")
                        .param("page", "0")
                        .param("sortBy", "title")
                        .param("sortDirection", "asc"))
                .andExpect(status().isOk())
                .andExpect(view().name("gettodos"))
                .andExpect(model().attribute("sortBy", is("title")))
                .andExpect(model().attribute("sortDirection", is("asc"))).andReturn();


    }

    @Test
    @DisplayName("Show create todo form works Ok")
    public void testShowCreateTodoForm() throws Exception{
        MvcResult mvcResult = mockMvc.perform(get("/createtodo"))
                .andExpect(status().isOk())
                .andExpect(view().name("create-todo"))
                .andExpect(model().attributeExists("users")).andReturn();
    }

    @Test
    @DisplayName("Create todo works Ok")
    public void testCreateTodo() throws Exception{
        MvcResult mvcResult = mockMvc.perform(post("/createtodo/create")
                        .param("title", "test title")
                        .param("userId", "1")
                        .param("completed", "false"))
                .andExpect(status().isCreated())
                .andExpect(view().name("created")).andReturn();
    }

    @Test
    @WithMockUser("jhonny_87")
    @DisplayName("Get user todos works Ok")
    public void testGetMyTodos() throws Exception{
        MvcResult mvcResult = mockMvc.perform(get("/mytodos")).andExpect(status().isOk())
                .andExpect(view().name("my-todos")).andReturn();
    }

    @Test
    @WithMockUser("jhonny_87")
    @DisplayName("Update todo works Ok")
    public void testUpdateTodo() throws Exception{
        MvcResult mvcResult = mockMvc.perform(post("/mytodos/1/update")
                        .param("title", "Updated Todo")
                        .param("completed", "true"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/mytodos")).andReturn();

        Todo todo = todoRepository.findById(1L).get();
        Assertions.assertTrue(todo.getTitle().contains("Updated Todo"));
    }

    @Test
    @WithMockUser("jhonny_87")
    @DisplayName("Delete todo works Ok")
    public void testDeleteTodo() throws Exception{
        MvcResult mvcResult = mockMvc.perform(get("/mytodos/1/delete"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/mytodos")).andReturn();

        Assertions.assertFalse(todoRepository.findById(1L).isPresent());
    }

    @Test
    @WithMockUser("miky_doe")
    @DisplayName("Update todo throws exception wrong user")
    public void testUpdateTodoThrowsException() throws Exception{
        MvcResult mvcResult = mockMvc.perform(post("/mytodos/1/update")
                        .param("title", "Updated Todo")
                        .param("completed", "true"))
                .andExpect(status().isForbidden())
                .andReturn();


        Assertions.assertTrue(mvcResult.getResolvedException().toString().contains("403 FORBIDDEN \"You are not the user asigned to this Todo\""));

    }

    @Test
    @WithMockUser("miky_doe")
    @DisplayName("Delete todo throws exception wrong user")
    public void testDeleteTodoThrowsException() throws Exception{
        MvcResult mvcResult = mockMvc.perform(get("/mytodos/1/delete"))
                .andExpect(status().isForbidden())
                .andReturn();


        Assertions.assertTrue(mvcResult.getResolvedException().toString().contains("403 FORBIDDEN \"You are not the user asigned to this Todo\""));
    }
}