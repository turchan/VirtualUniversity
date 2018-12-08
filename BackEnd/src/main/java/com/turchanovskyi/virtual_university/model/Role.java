package com.turchanovskyi.virtual_university.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
	ADMIN,
	STUDENT,
	PROFESSOR;

	@Override
	public String getAuthority() {
		return name();
	}
}
