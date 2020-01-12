package com.turchanovskyi.virtual_university.ItegrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turchanovskyi.virtual_university.VirtualUniversityApplication;
import com.turchanovskyi.virtual_university.interfaces.UserService;
import com.turchanovskyi.virtual_university.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = VirtualUniversityApplication.class)
@AutoConfigureMockMvc
@ContextConfiguration
@TestPropertySource(
        locations = "classpath:application.properties"
)
public class UserControllerIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser
    public void findAllUsers_thenStatus200() throws Exception {

        List<User> userList = new ArrayList<>();
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        when(userService.findAll()).thenReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*]", hasSize(3)));
    }

    @Test
    @WithMockUser
    public void findUserById_thenStatus200() throws Exception {

        User user = new User(1L,"qwer", "qwer", "qwer", "qwer", "qwer", "qwer@qwer.qwer", "qwer");

        when(userService.findById(user.getUser_id())).thenReturn(user);

        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/user/{userId}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.user_id").value(1L));
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    public void whenPutUser_thenUpdateUser() throws Exception {

        User user = new User(1L,"qwer", "qwer", "qwer", "qwer", "qwer", "qwer@qwer.qwer", "qwer");
        user.setLogin("asdf");

        when(userService.save(user)).thenReturn(user);

        this.mockMvc.perform(MockMvcRequestBuilders
                .put("/user/update")
                .content(asJsonString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.login").value("asdf"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("qwer"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("qwer@qwer.qwer"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void whenDeleteUser_thenDeleteUser() throws Exception {

        User user = new User(1L,"qwer", "qwer", "qwer", "qwer", "qwer", "qwer@qwer.qwer", "qwer");

        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/user/delete/{userId}", 1L)
                .content(asJsonString(user)))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
