package com.turchanovskyi.virtual_university.message.request;

import java.util.Set;

public class SignUpForm {

	private String login;

	private String password;

	private String name;

	private String surname;

	private String country;

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
