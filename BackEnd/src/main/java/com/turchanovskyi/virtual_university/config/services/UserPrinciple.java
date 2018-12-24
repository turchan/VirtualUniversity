package com.turchanovskyi.virtual_university.config.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.turchanovskyi.virtual_university.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Objects;

public class UserPrinciple implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String login;
	@JsonIgnore
	private String password;
	private String name;
	private String surname;
	private String country;
	private String email;
	private String city;

	private Collection<? extends GrantedAuthority> authorities;

	public UserPrinciple(Long id, String login,
	                     String password, String name, String surname,
	                     String country, String email, String city,
	                     Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.country = country;
		this.email = email;
		this.city = city;
		this.authorities = authorities;
	}

	public static UserPrinciple build(User user)
	{
		List<GrantedAuthority> authorities = user.getRole_id().stream().map(role ->
				new SimpleGrantedAuthority(role.getName().name())
		).collect(Collectors.toList());

		return new UserPrinciple(
				user.getUser_id(),
				user.getLogin(),
				user.getPassword(),
				user.getName(),
				user.getSurname(),
				user.getCountry(),
				user.getEmail(),
				user.getCity(),
				authorities
		);
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getCountry() {
		return country;
	}

	public String getEmail() {
		return email;
	}

	public String getCity() {
		return city;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;

		UserPrinciple user = (UserPrinciple) obj;
		return Objects.equals(id, user.id);
	}
}
