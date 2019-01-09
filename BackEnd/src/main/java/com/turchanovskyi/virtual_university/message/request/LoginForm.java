package com.turchanovskyi.virtual_university.message.request;

import javax.validation.constraints.NotEmpty;

public class LoginForm {

	@NotEmpty(message = "Login should be entered")
	private String login;

	@NotEmpty(message = "Login should be entered")
	private String password;

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
}