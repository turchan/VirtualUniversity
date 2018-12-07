package com.turchanovskyi.virtual_university.interfaces;

import com.turchanovskyi.virtual_university.model.User;

import java.util.List;

public interface UserService {

	List<User> findAll();
	User findById(Long id);
	User save(User user);
	void delete(User user);
}
