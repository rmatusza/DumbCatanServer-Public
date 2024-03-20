package com.dumbcatan.entity;

public class AuthenticationRequest {
	
	private String username;
	private String password;
	private boolean tokenPresent;
	
	public boolean isTokenPresent() {
		return tokenPresent;
	}

	public void setTokenPresent(boolean tokenPresent) {
		this.tokenPresent = tokenPresent;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public AuthenticationRequest() {
			
		}

	public AuthenticationRequest(String username, String password, boolean tokenPresent) {
		this.username = username;
		this.password = password;
		this.tokenPresent = tokenPresent;
	}
}
