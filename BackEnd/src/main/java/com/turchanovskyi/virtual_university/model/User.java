package com.turchanovskyi.virtual_university.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long user_id;

	@Column(name = "login")
	private String login;

	@Column(name = "password")
	private String password;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	@Column(name = "country")
	private String country;

	@Column(name = "email")
	private String email;

	@Column(name = "city")
	private String city;

	@Enumerated(EnumType.STRING)
	@JoinTable(name = "roles", joinColumns = @JoinColumn(name = "role_id"))
	@Column(name = "role_id")
	private Role role_id;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "users_courses",
				joinColumns = @JoinColumn(name = "user_id"),
				inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> coursesList = new ArrayList();

	public User() {
	}

	public User(String login, String password, String name, String surname, String country, String email, String city, Role role_id) {
		this.login = login;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.country = country;
		this.email = email;
		this.city = city;
		this.role_id = role_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(getRole_id());
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
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

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Role getRole_id() {
		return role_id;
	}

	public void setRole_id(Role role_id) {
		this.role_id = role_id;
	}

	public List<Course> getCoursesList() {
		return coursesList;
	}

	public void setCoursesList(List<Course> coursesList) {
		this.coursesList = coursesList;
	}
}
