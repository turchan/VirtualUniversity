package com.turchanovskyi.virtual_university.implementation;

import com.turchanovskyi.virtual_university.interfaces.UserService;
import com.turchanovskyi.virtual_university.model.User;
import com.turchanovskyi.virtual_university.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional(readOnly = true)
	@Override
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public User findById(Long id) {
		return userRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Transactional
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Transactional
	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		return userRepository.findByEmail(s);
	}
}
