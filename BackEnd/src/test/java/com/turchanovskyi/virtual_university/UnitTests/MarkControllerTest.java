package com.turchanovskyi.virtual_university.UnitTests;

import com.turchanovskyi.virtual_university.implementation.MarkServiceImpl;
import com.turchanovskyi.virtual_university.model.Course;
import com.turchanovskyi.virtual_university.model.Mark;
import com.turchanovskyi.virtual_university.model.User;
import com.turchanovskyi.virtual_university.repository.MarkRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class MarkControllerTest {

    @InjectMocks
    MarkServiceImpl markService;

    @Mock
    MarkRepository markRepository;

    @Test
    public void main() {
        List<Mark> markList = new ArrayList<>();
        Mark mark1 = new Mark();
        Mark mark2 = new Mark();
        Mark mark3 = new Mark();

        markList.add(mark1);
        markList.add(mark2);
        markList.add(mark3);

        when(markRepository.findAll()).thenReturn(markList);

        List<Mark> materials = markService.findAll();

        assertThat(materials.size()).isEqualTo(3);
        verify(markRepository, times(1)).findAll();
    }

    @Test
    public void addMark() {
        Course course = new Course();
        User user = new User();
        Mark mark = new Mark(1L, "qwer", 1, user, course);

        mark.setUser(user);
        mark.setCourse(course);
        user.getMarkList().add(mark);

        when(markRepository.save(mark)).thenReturn(mark);

        Mark addMark = markService.save(mark);

        assertEquals("qwer", user.getMarkList().get(0).getTitle());
        verify(markRepository, times(1)).save(mark);
    }

    @Test
    public void updateMark() {
        Course course = new Course();
        User user = new User();
        Mark mark = new Mark(1L, "qwer", 1, user, course);
        mark.setTitle("asdf");

        when(markRepository.save(mark)).thenReturn(mark);

        Mark updateMark = markService.save(mark);

        assertEquals("asdf", mark.getTitle());
        verify(markRepository, times(1)).save(mark);
    }

    @Test
    public void deleteMark() {
        Mark mark = new Mark();

        markService.deleteById(1L);

        verify(markRepository,times(1)).deleteById(1L);
    }
}
