package com.turchanovskyi.virtual_university.UnitTests;

import com.turchanovskyi.virtual_university.implementation.UserServiceImpl;
import com.turchanovskyi.virtual_university.interfaces.UserService;
import com.turchanovskyi.virtual_university.model.User;
import com.turchanovskyi.virtual_university.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserControllerTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Test
    public void main() {
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        when(userRepository.findAll()).thenReturn(userList);

        List<User> users = userService.findAll();

        assertThat(users.size()).isEqualTo(3);
        verify(userRepository, times(1)).findAll();
    }

    /*@Test
    public void getUser() {
        User user = new User("qwer", "qwer", "qwer", "qwer", "qwer", "qwer@qwer.qwer", "qwer");

        when(userService.findById(user.getUser_id())).thenReturn(user);
    }*/

    @Test
    public void searchUser() {

        when(userRepository.findByLogin("qwer")).thenReturn(new User("qwer", "qwer", "qwer", "qwer", "qwer", "qwer", "qwer"));

        User user = userService.findByLogin("qwer");

        assertEquals("qwer", user.getLogin());
    }

    @Test
    public void updateUser() {
        User user = new User("qwer", "qwer", "qwer", "qwer", "qwer", "qwer", "qwer");
        user.setLogin("asdf");

        when(userRepository.save(user)).thenReturn(user);

        User updateUser = userService.save(user);

        assertEquals("asdf", updateUser.getLogin());
    }

    @Test
    public void deleteUser() {

        User user = new User();

        userService.deleteById(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }
}
