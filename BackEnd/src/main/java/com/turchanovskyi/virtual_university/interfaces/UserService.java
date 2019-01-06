package com.turchanovskyi.virtual_university.interfaces;

import com.turchanovskyi.virtual_university.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {

	Iterable<User> findAll();
	User findById(Long id);
	List<User> findBySurname(String surname);
	User findByLogin(String login);
	User save(User user);
	void deleteById(Long id);
}
