package com.turchanovskyi.virtual_university.ItegrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turchanovskyi.virtual_university.VirtualUniversityApplication;
import com.turchanovskyi.virtual_university.interfaces.CourseService;
import com.turchanovskyi.virtual_university.model.Course;
import com.turchanovskyi.virtual_university.repository.CourseRepository;
import org.assertj.core.internal.bytebuddy.matcher.ElementMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
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
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
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

    @MockBean
    private CourseService courseService;

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

        List<Course> courseList = new ArrayList<>();
        Course course1 = new Course("qwer", "qwer", "qwer", "qwer", "qwer");
        Course course2 = new Course("asdf", "asdf", "asdf", "asdf", "asdf");
        Course course3 = new Course("zxcv", "zxcv", "zcxv", "zxcv", "zxcv");

        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);

        when(courseService.findAll()).thenReturn(courseList);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/course")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*]", hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("qwer"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].professor").value("zcxv"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void whenPostCourse_thenCreateCourse() throws Exception {

        Course course = new Course(1L, "asdfasdfasdf", "qwerqwerqwerqwer", "asdf", "asdf", "asdf");

        when(courseService.save(any())).thenReturn(course);

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/course/create")
                .content(asJsonString(course))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").exists());
    }

    @Test
    @WithMockUser
    public void findCourseById_thenStatus200() throws Exception {

        Course course = new Course(1L, "qwer", "qwer", "Qwer", "qwer", "Qwer");

        when(courseService.findById(course.getCourse_id())).thenReturn(course);

        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/course/{courseId}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.course_id").value(1L));
    }

    @Test
    @WithMockUser
    public void getCourseByTitle_thenStatus200() throws Exception {

        Course course = new Course(1L, "asdfasdfasdf", "qwer", "Qwer", "qwer", "Qwer");

        List<Course> target = new ArrayList<>();
        target.add(course);

        when(courseService.findByTitle(course.getTitle())).thenReturn(target);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/course/search/{title}", "asdfasdfasdf")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].title").value("asdfasdfasdf"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void whenPutCourse_thenUpdateCourse() throws Exception {

        Course course = new Course("qwer", "qwer", "qwer", "qwer", "qwer");
        course.setDescription("asdf");

        when(courseService.save(course)).thenReturn(course);

        this.mockMvc.perform(MockMvcRequestBuilders
                .put("/course/update")
                .content(asJsonString(course))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("qwer"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("asdf"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void whenDeleteCourse_thenDeleteCourse() throws Exception {

        Course course = new Course(1L,"qwer", "qwer", "qwer", "qwer", "qwer");

        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/course/{courseId}", 1L)
                .content(asJsonString(course)))
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
