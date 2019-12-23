package com.turchanovskyi.virtual_university.ItegrationTests;

<<<<<<< HEAD
import com.fasterxml.jackson.databind.ObjectMapper;
=======
>>>>>>> 856a85ce0f6dac87e443df0bcfc254b7a835ba5c
import com.turchanovskyi.virtual_university.VirtualUniversityApplication;
import com.turchanovskyi.virtual_university.controllers.CourseController;
import com.turchanovskyi.virtual_university.interfaces.CourseService;
import com.turchanovskyi.virtual_university.model.Course;
import com.turchanovskyi.virtual_university.repository.CourseRepository;
import net.minidev.json.JSONUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
<<<<<<< HEAD
import org.springframework.security.test.context.support.WithSecurityContext;
=======
>>>>>>> 856a85ce0f6dac87e443df0bcfc254b7a835ba5c
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
<<<<<<< HEAD
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
=======
>>>>>>> 856a85ce0f6dac87e443df0bcfc254b7a835ba5c
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
public class CourseControllerIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

<<<<<<< HEAD
=======
    @MockBean
    private CourseService courseService;

    @MockBean
    private CourseRepository courseRepository;

>>>>>>> 856a85ce0f6dac87e443df0bcfc254b7a835ba5c
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser
    public void findAllCourses_thenStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/course")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
<<<<<<< HEAD
    @WithMockUser(roles = "ADMIN")
    public void whenPostCourse_thenCreateCourse() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/course/create")
                .content(asJsonString(new Course(1L,"asdfasdfasdf", "qwerqwerqwerqwer", "asdf", "asdf", "asdf")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").exists());
    }

    @Test
    @WithMockUser
    public void findCourseById_thenStatus200() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/course/{courseId}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.course_id").value(2L));
    }

    @Test
    @WithMockUser
    public void getCourseByTitle_thenStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/course/search/asdfasdfasdf")
=======
    @WithMockUser
    public void getCourseByTitle_thenStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/course/search/SUM")
>>>>>>> 856a85ce0f6dac87e443df0bcfc254b7a835ba5c
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
<<<<<<< HEAD
    @WithMockUser(roles = "ADMIN")
    public void whenPutCourse_thenUpdateCourse() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
            .put("/course/update")
            .content(asJsonString(new Course("qwer", "qwerqwerqwerqwer", "qwer", "Qwer", "qwer")))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("qwer"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("qwerqwerqwerqwer"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Qwer"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void whenDeleteCourse_thenDeleteCourse() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/course/{courseId}", 1L))
                .andExpect(status().isNoContent());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
=======
    @WithMockUser
    public void whenPostCourse_thenCreateCourse() throws Exception {
        Course course = new Course("zxcvzxcv", "zxcvzxcv", "Qwezxcvr", "Qwer", "zxcvzxcv");
        given(courseService.save(course)).willReturn(course);

        mockMvc.perform(post("/course/create").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(course)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is("zxcvzxcv")));
        verify(courseService, VerificationModeFactory.times(1)).save(course);
        reset(courseService);
>>>>>>> 856a85ce0f6dac87e443df0bcfc254b7a835ba5c
    }
}
