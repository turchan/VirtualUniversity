package com.turchanovskyi.virtual_university.repository;

import com.turchanovskyi.virtual_university.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByLogin(String login);
	List<User> findBySurname(String surname);
}
