package com.turchanovskyi.virtual_university.UnitTests;

import com.turchanovskyi.virtual_university.implementation.MaterialServiceImpl;
import com.turchanovskyi.virtual_university.interfaces.MaterialService;
import com.turchanovskyi.virtual_university.model.Course;
import com.turchanovskyi.virtual_university.model.Mark;
import com.turchanovskyi.virtual_university.model.Material;
import com.turchanovskyi.virtual_university.model.User;
import com.turchanovskyi.virtual_university.repository.MaterialRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class MaterialControllerTest {

    @InjectMocks
    MaterialServiceImpl materialService;

    @Mock
    MaterialRepository materialRepository;

    @Test
    public void main() {
        List<Material> materialList = new ArrayList<>();
        Material material1 = new Material();
        Material material2 = new Material();
        Material material3 = new Material();

        materialList.add(material1);
        materialList.add(material2);
        materialList.add(material3);

        when(materialRepository.findAll()).thenReturn(materialList);

        List<Material> materials = materialService.findAll();

        assertThat(materials.size()).isEqualTo(3);
        verify(materialRepository, times(1)).findAll();
    }

    @Test
    public void addMaterial() {
        Material material = new Material("qwer", "Qwer");
        Course course = new Course("qwer", "qwer", "qwer", "Qwer", "qwer");
        material.setCourse(course);
        course.getMaterialList().add(material);

        when(materialRepository.save(material)).thenReturn(material);

        Material addMaterial = materialService.save(material);

        assertEquals("qwer", course.getMaterialList().get(0).getTitle());
        verify(materialRepository, times(1)).save(material);
    }

    @Test
    public void updateMaterial() {
        Material material = new Material("qwer", "qwer");
        material.setDescription("asdf");

        when(materialRepository.save(material)).thenReturn(material);

        Material updateMaterial = materialService.save(material);

        assertEquals("asdf", updateMaterial.getDescription());
        verify(materialRepository, times(1)).save(material);
    }

    @Test
    public void deleteMaterial() {
        Material material = new Material();

        materialService.deleteById(1L);

        verify(materialRepository, times(1)).deleteById(1L);
    }
}
