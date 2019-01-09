package com.turchanovskyi.virtual_university.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long user_id;

	@NotEmpty(message = "Login cannot be empty")
	@Column(name = "login")
	private String login;

	@NotNull(message = "Password should be entered")
	@Size(min = 6, message = "Password must have at least 6 characters")
	@Column(name = "password")
	private String password;

	@NotEmpty(message = "Name should be entered")
	@Column(name = "name")
	private String name;

	@NotEmpty(message = "Surname should be entered")
	@Column(name = "surname")
	private String surname;

	@Column(name = "country")
	private String country;

	@NotEmpty(message = "Email cannot be empty")
	@Email
	@Column(name = "email")
	private String email;

	@Column(name = "city")
	private String city;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_roles",
				joinColumns = @JoinColumn(name = "user_id"),
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> role_id = new HashSet<>();

	@JsonIgnoreProperties({"userList", "markList"})
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "users_courses",
				joinColumns = @JoinColumn(name = "user_id"),
				inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> coursesList = new ArrayList<>();

	@JsonIgnoreProperties("user")
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Mark> markList = new ArrayList<>();

	public User() {
	}

	public User(String login, String password, String name, String surname, String country, String email, String city) {
		this.login = login;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.country = country;
		this.email = email;
		this.city = city;
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

	public String getPassword() {
		return password;
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

	public Set<Role> getRole_id() {
		return role_id;
	}

	public void setRole_id(Set<Role> role_id) {
		this.role_id = role_id;
	}

	public List<Course> getCoursesList() {
		return coursesList;
	}

	public void setCoursesList(List<Course> coursesList) {
		this.coursesList = coursesList;
	}

	public List<Mark> getMarkList() {
		return markList;
	}

	public void setMarkList(List<Mark> markList) {
		this.markList = markList;
	}
}
