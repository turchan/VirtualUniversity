package com.turchanovskyi.virtual_university.config.services;

import com.turchanovskyi.virtual_university.model.User;
import com.turchanovskyi.virtual_university.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

		User user = userRepository.findByLogin(login);

		if (user == null) {
			throw new UsernameNotFoundException(
					"No user found with login: " + login);
		}

		return UserPrinciple.build(user);
	}
}