package springtodo.springtodo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import springtodo.springtodo.DTOs.TodoDTO;
import springtodo.springtodo.models.Todo;
import springtodo.springtodo.repositories.TodoRepository;
import springtodo.springtodo.repositories.UserRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
public class ControllerTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @DisplayName("Get Todos works ok")
    void getTodosWorkOk() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/todos/get-todos").param("size","10").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        Assertions.assertTrue(mvcResult.getResponse().getContentAsString().contains("[\"Make test to controllers\",\"jhonny_87\",\"Country\",false],[\"Make test to controllers\",\"jhonny_87\",\"Country\",false],[\"Make test to controllers\",\"jhonny_87\",\"Country\",false],[\"Make test to controllers\",\"jhonny_87\",\"Country\",false],[\"Make test to controllers\",\"jhonny_87\",\"Country\",false],[\"Make test to controllers\",\"jhonny_87\",\"Country\",false],[\"Make test to controllers\",\"jhonny_87\",\"Country\",false],[\"Make test to controllers\",\"jhonny_87\",\"Country\",false],[\"Make test to controllers\",\"jhonny_87\",\"Country\",false],[\"Do test to controllers\",\"jhonny_87\",\"Country\",false]"));
    }

    @Test
    @DisplayName("Get Todos using search text works ok")
    void getTodosWithSearchTextWorksOk() throws Exception{
        MvcResult mvcResult = mockMvc.perform(get("/todos/get-todos").param("searchText","Do").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        Assertions.assertTrue(mvcResult.getResponse().getContentAsString().contains("[\"Do test to controllers\",\"jhonny_87\",\"Country\",false],[\"Do the laundry\",\"miky_doe\",\"Country\",false],[\"Do a list of Todos\",\"miky_doe\",\"Country\",true],[\"Another test todo\",\"miky_doe\",\"Country\",false],[\"Do test to controllers\",\"miky_doe\",\"Country\",false]"));
    }

    @Test
    @DisplayName("Get Todos using username works ok")
    void getTodosWithUserNameWorksOk() throws Exception{
        MvcResult mvcResult = mockMvc.perform(get("/todos/get-todos").param("userName","jhonny_87").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        Assertions.assertTrue(mvcResult.getResponse().getContentAsString().contains("[\"Make test to controllers\",\"jhonny_87\",\"Country\",false],[\"Make test to controllers\",\"jhonny_87\",\"Country\",false],[\"Make test to controllers\",\"jhonny_87\",\"Country\",false],[\"Make test to controllers\",\"jhonny_87\",\"Country\",false],[\"Make test to controllers\",\"jhonny_87\",\"Country\",false],[\"Make test to controllers\",\"jhonny_87\",\"Country\",false],[\"Make test to controllers\",\"jhonny_87\",\"Country\",false],[\"Make test to controllers\",\"jhonny_87\",\"Country\",false],[\"Make test to controllers\",\"jhonny_87\",\"Country\",false],[\"Do test to controllers\",\"jhonny_87\",\"Country\",false]"));
    }

    @Test
    @DisplayName("Get Todos using search text and username works ok")
    void getTodosWithUserNameAndTextWorksOk() throws Exception{
        MvcResult mvcResult = mockMvc.perform(get("/todos/get-todos").param("userName","miky_doe").param("searchText","do").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        Assertions.assertTrue(mvcResult.getResponse().getContentAsString().contains("[\"Do the laundry\",\"miky_doe\",\"Country\",false],[\"Do a list of Todos\",\"miky_doe\",\"Country\",true],[\"Another test todo\",\"miky_doe\",\"Country\",false],[\"Do test to controllers\",\"miky_doe\",\"Country\",false]"));
    }

    @Test
    @DisplayName("Create Todo works ok")
    void createTodoWorksOk() throws Exception{
        TodoDTO todoDTO = new TodoDTO("Test creation",true, "miky_doe");
        String body = objectMapper.writeValueAsString(todoDTO);
        MvcResult mvcResult = mockMvc.perform(post("/todos/create").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        Assertions.assertTrue(todoRepository.findById(21L).isPresent());
    }

    @Test
    @DisplayName("Edit Todo works ok")
    @WithMockUser("jhonny_87")
    void editTodoWorksOk() throws Exception {
        TodoDTO todoDTO = new TodoDTO("Make test to controllers 2",true, "");
        String body = objectMapper.writeValueAsString(todoDTO);
        MvcResult mvcResult = mockMvc.perform(put("/todos/edit").param("id","1").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        Assertions.assertTrue(mvcResult.getResponse().getContentAsString().contains("\"id\":1,\"title\":\"Make test to controllers 2\",\"completed\":true,\"todoUser\":{\"userId\":1,\"name\":\"Jhon Doe\""));
    }

    @Test
    @DisplayName("Edit Todo throws exception wrong user")
    @WithMockUser("miky_doe")
    void editTodoThrowsExceptionNotTheUserOfTheTodo() throws Exception {
        TodoDTO todoDTO = new TodoDTO("Make test to controllers 2",true, "");
        String body = objectMapper.writeValueAsString(todoDTO);
        MvcResult mvcResult = mockMvc.perform(put("/todos/edit").param("id","1").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden()).andReturn();
        Assertions.assertTrue(mvcResult.getResolvedException().toString().contains("403 FORBIDDEN \"You are not the user asigned to this Todo\""));

    }

    @Test
    @DisplayName("Delete Todo works ok")
    @WithMockUser("jhonny_87")
    void deleteTodoWorksOk() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/todos/delete").param("todoId","3").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        Assertions.assertFalse(todoRepository.findById(3L).isPresent());
    }

    @Test
    @DisplayName("Delete Todo throws exception wrong user")
    @WithMockUser("miky_doe")
    void deleteTodoThrowsExceptionNotCorrectUser() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/todos/delete").param("todoId","2").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden()).andReturn();
        Assertions.assertTrue(mvcResult.getResolvedException().toString().contains("403 FORBIDDEN \"You are not the user asigned to this Todo\""));
    }

}
