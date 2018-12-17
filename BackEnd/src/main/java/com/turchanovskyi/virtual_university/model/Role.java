package com.turchanovskyi.virtual_university.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Table;

@Table(name = "roles")
public enum Role implements GrantedAuthority {
		ADMIN,
		STUDENT,
		PROFESSOR;

	@Override
	public String getAuthority() {
		return name();
	}
}
