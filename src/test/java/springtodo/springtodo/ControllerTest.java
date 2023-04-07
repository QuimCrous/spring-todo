package springtodo.springtodo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
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

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void getTodosWorkOk() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/todos/get-todos").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        //Assertions.assertTrue(mvcResult.getResponse().getContentAsString().contains("[\"Make test to controllers\",\"jhonny_87\",\"Country\",false],[\"Make test to controllers\",\"jhonny_87\",\"Country\",false],[\"Make test to controllers\",\"jhonny_87\",\"Country\",false],[\"Make test to controllers\",\"jhonny_87\",\"Country\",false],[\"Make test to controllers\",\"jhonny_87\",\"Country\",false],[\"Make test to controllers\",\"jhonny_87\",\"Country\",false],[\"Make test to controllers\",\"jhonny_87\",\"Country\",false],[\"Make test to controllers\",\"jhonny_87\",\"Country\",false],[\"Make test to controllers\",\"jhonny_87\",\"Country\",false],[\"Do test to controllers\",\"jhonny_87\",\"Country\",false]"));
    }

    @Test
    void getTodosWithSearchTextWorksOk() throws Exception{
        MvcResult mvcResult = mockMvc.perform(get("/todos/get-todos").param("searchText","Do").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        //Assertions.assertTrue(mvcResult.getResponse().getContentAsString().contains("[\"Do test to controllers\",\"jhonny_87\",\"Country\",false]]"));
    }

    @Test
    void getTodosWithUserNameWorksOk() throws Exception{
        MvcResult mvcResult = mockMvc.perform(get("/todos/get-todos").param("userName","jhonny_87").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        //Assertions.assertTrue(mvcResult.getResponse().getContentAsString().contains("[\"Do test to controllers\",\"jhonny_87\",\"Country\",false]]"));
    }

}
