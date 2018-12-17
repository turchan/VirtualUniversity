package com.turchanovskyi.virtual_university.interfaces;

import com.turchanovskyi.virtual_university.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

	Iterable<User> findAll();
	User findById(Long id);
	User findByLogin(String login);
	User save(User user);
	void deleteById(Long id);
}
