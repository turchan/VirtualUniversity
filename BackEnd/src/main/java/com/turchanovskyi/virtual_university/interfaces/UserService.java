package com.turchanovskyi.virtual_university.interfaces;

import com.turchanovskyi.virtual_university.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

	Iterable<User> findAll();
	User findById(Long id);
	User findByEmail(String email);
	User save(User user);
	void deleteById(Long id);

	@Override
	UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;
}
