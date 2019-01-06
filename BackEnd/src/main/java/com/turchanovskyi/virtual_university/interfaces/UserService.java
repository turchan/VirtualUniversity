package com.turchanovskyi.virtual_university.interfaces;

import com.turchanovskyi.virtual_university.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService<T extends User> {

	Iterable<T> findAll();
	T findById(Long id);
	List<T> findBySurname(String surname);
	T findByLogin(String login);
	T save(User user);
	void deleteById(Long id);
}
