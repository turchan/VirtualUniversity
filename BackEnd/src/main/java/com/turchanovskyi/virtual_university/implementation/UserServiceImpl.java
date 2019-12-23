package com.turchanovskyi.virtual_university.implementation;

import com.turchanovskyi.virtual_university.interfaces.UserService;
import com.turchanovskyi.virtual_university.model.User;
import com.turchanovskyi.virtual_university.repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@Service("userService")
public class UserServiceImpl implements UserService<User> {

	private final UserRepository<User> userRepository;

	public UserServiceImpl(UserRepository<User> userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional(readOnly = true)
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public User findById(Long id) {
		return userRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	@Override
	public User findByLogin(String login) {
		return userRepository.findByLogin(login);
	}

	@Transactional(readOnly = true)
	@Override
	public List<User> findBySurname(String surname) {
		return userRepository.findBySurname(surname);
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
}
