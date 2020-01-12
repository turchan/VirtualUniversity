package com.turchanovskyi.virtual_university.ItegrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turchanovskyi.virtual_university.VirtualUniversityApplication;
import com.turchanovskyi.virtual_university.interfaces.CourseService;
import com.turchanovskyi.virtual_university.interfaces.MarkService;
import com.turchanovskyi.virtual_university.model.Course;
import com.turchanovskyi.virtual_university.model.Mark;
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
public class MarkContollerIntergrationTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MarkService markService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser
    public void findAllMarks_thenStatus200() throws Exception {

        List<Mark> markList = new ArrayList<>();
        Mark mark1 = new Mark();
        Mark mark2 = new Mark();
        Mark mark3 = new Mark();

        markList.add(mark1);
        markList.add(mark2);
        markList.add(mark3);

        when(markService.findAll()).thenReturn(markList);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/marks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*]", hasSize(3)));
    }

    @Test
    @WithMockUser
    public void findMarkById_thenStatus200() throws Exception {

        Mark mark = new Mark(1L);

        when(markService.findById(mark.getMark_id())).thenReturn(mark);

        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/marks/{markId}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mark_id").value(1L));
    }

    @Test
    @WithMockUser(roles = "PROFESSOR")
    public void updateMark_thenStatus200() throws Exception {

        Course course = new Course();
        User user = new User();
        Mark mark = new Mark(1L, "qwer", 1, user, course);
        mark.setMark(5);

        when(markService.save(mark)).thenReturn(mark);

        this.mockMvc.perform(MockMvcRequestBuilders
                .put("/marks/update")
                .content(asJsonString(mark))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mark").value(5));
    }

    @Test
    @WithMockUser(roles = "PROFESSOR")
    public void whenDeleteMark_thenStatus200() throws Exception {

        Mark mark = new Mark(1L);

        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/marks/delete/{markId}", 1)
                .content(asJsonString(mark)))
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
