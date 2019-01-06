package com.turchanovskyi.virtual_university.repository;

import com.turchanovskyi.virtual_university.model.Role;
import com.turchanovskyi.virtual_university.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	Optional<Role> findByName(RoleName roleName);
}
