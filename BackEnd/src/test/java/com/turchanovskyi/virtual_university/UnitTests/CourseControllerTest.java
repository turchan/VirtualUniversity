package com.turchanovskyi.virtual_university.UnitTests;

import com.turchanovskyi.virtual_university.implementation.CourseServiceImpl;
import com.turchanovskyi.virtual_university.model.Course;
import com.turchanovskyi.virtual_university.model.User;
import com.turchanovskyi.virtual_university.repository.CourseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CourseControllerTest {

    @InjectMocks
    CourseServiceImpl courseServiceImpl;

    @Mock
    CourseRepository courseRepository;

    @Test
    public void main() {
        List<Course> courseList = new ArrayList<>();
        Course course1 = new Course("qwer", "qwer", "qwer", "qwer", "qwer");
        Course course2 = new Course("asdf", "asdf", "asdf", "asdf", "asdf");
        Course course3 = new Course("zxcv", "zxcv", "zcxv", "zxcv", "zxcv");

        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);

        when(courseRepository.findAll()).thenReturn(courseList);

        List<Course> courses = courseServiceImpl.findAll();

        assertThat(courses.size()).isEqualTo(3);
        verify(courseRepository, times(1)).findAll();
    }

    @Test
    public void getCourse() {
        when(courseRepository.findByTitle("qwer")).thenReturn(Collections.singletonList(new Course("qwer", "qwer", "qwer", "qwer", "qwer")));

        List<Course> courseList = courseServiceImpl.findByTitle("qwer");

        assertEquals("qwer", courseList.get(0).getTitle());
    }

    @Test
    public void createCourse() {

        Course course = new Course("qwer", "qwer", "qwer", "qwer", "qwer");

        when(courseRepository.save(course)).thenReturn(course);

        Course newCourse = courseServiceImpl.save(course);

        assertEquals("qwer", newCourse.getTitle());

        verify(courseRepository, times(1)).save(course);

    }

    @Test
    public void updateCourse() {

        Course course = new Course("qwer", "qwer", "qwer", "qwer", "qwer");
        course.setDescription("asdf");

        when(courseRepository.save(course)).thenReturn(course);

        Course updateCourse = courseServiceImpl.save(course);

        assertEquals("asdf", updateCourse.getDescription());
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    public void addUser() {
        Course course = new Course("qwer", "qwer", "qwer", "qwer", "qwer");
        User user = new User("qwer", "qwer", "qwer", "qwer", "qwer", "qwer", "qwer");

        course.getUserList().add(user);
        user.getCoursesList().add(course);

        when(courseRepository.save(course)).thenReturn(course);

        Course addUserCourse = courseServiceImpl.save(course);

        assertEquals(user.getName(), addUserCourse.getUserList().get(0).getName());
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    public void deleteCourse() {
        Course course = new Course();

        courseServiceImpl.deleteById(1L);

        verify(courseRepository, times(1)).deleteById(1L);
    }
}
