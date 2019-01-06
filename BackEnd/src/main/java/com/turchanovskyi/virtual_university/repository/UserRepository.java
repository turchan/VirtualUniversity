package com.turchanovskyi.virtual_university.repository;

import com.turchanovskyi.virtual_university.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository<T extends User> extends JpaRepository<T, Long> {
	T findByLogin(String login);
	List<T> findBySurname(String surname);
}
