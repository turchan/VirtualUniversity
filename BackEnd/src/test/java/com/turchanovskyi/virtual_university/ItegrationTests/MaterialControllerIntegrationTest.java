package com.turchanovskyi.virtual_university.ItegrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turchanovskyi.virtual_university.VirtualUniversityApplication;
import com.turchanovskyi.virtual_university.interfaces.MaterialService;
import com.turchanovskyi.virtual_university.model.Material;
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
public class MaterialControllerIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MaterialService materialService;

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

        List<Material> materialList = new ArrayList<>();
        Material material1 = new Material();
        Material material2 = new Material();
        Material material3 = new Material();

        materialList.add(material1);
        materialList.add(material2);
        materialList.add(material3);

        when(materialService.findAll()).thenReturn(materialList);

        mockMvc.perform(MockMvcRequestBuilders.get("/materials")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*]", hasSize(3)));
    }

    @Test
    @WithMockUser
    public void getMaterialById_thenStatus200() throws Exception {

        Material material = new Material(1L);

        when(materialService.findById(material.getMaterial_id())).thenReturn(material);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/materials/{materialId}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.material_id").value(1L));
    }

    /*@Test
    @WithMockUser(roles = "PROFESSOR")
    public void whenUpdateMaterial_thenStatus200() throws Exception {

        Material material = new Material("qwer", "qwer");
        material.setDescription("asdf");

        when(materialService.save(material)).thenReturn(material);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/materials/update")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("asdf"));
    }*/

    @Test
    @WithMockUser(roles = "PROFESSOR")
    public void whenDeleteMaterial_thenStatus200() throws Exception {

        Material material = new Material(1L);

        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/materials/delete/{materialId}", 1L)
                .content(asJsonString(material)))
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
