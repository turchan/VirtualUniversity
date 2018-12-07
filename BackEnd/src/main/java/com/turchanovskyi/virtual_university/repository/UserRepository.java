package com.turchanovskyi.virtual_university.repository;

import com.turchanovskyi.virtual_university.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {}
