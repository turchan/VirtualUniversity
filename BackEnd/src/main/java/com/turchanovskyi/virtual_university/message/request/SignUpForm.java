package com.turchanovskyi.virtual_university.message.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

public class SignUpForm {

	@NotEmpty(message = "Login should be entered")
	private String login;

	@NotEmpty(message = "Password should be entered")
	@Size(min = 6, message = "Password should have at least 6 characters")
	private String password;

	@NotEmpty(message = "Login should be entered")
	private String name;

	@NotEmpty(message = "Login should be entered")
	private String surname;

	private String country;

	@NotEmpty(message = "Login should be entered")
	@Email
	private String email;

	private String city;

	private Set<String> role_id;

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

	public Set<String> getRole_id() {
		return role_id;
	}

	public void setRole_id(Set<String> role_id) {
		this.role_id = role_id;
	}
}
