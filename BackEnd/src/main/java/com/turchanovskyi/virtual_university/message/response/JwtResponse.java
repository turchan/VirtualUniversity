package com.turchanovskyi.virtual_university.message.response;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private String login;
	private Collection<? extends GrantedAuthority> authorities;

	public JwtResponse(String accessToken, String login, Collection<? extends GrantedAuthority> authorities) {
		this.token = accessToken;
		this.login = login;
		this.authorities = authorities;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
}