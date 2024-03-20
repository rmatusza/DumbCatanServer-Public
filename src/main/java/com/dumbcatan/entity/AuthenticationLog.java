package com.dumbcatan.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="authentication_logs")
public class AuthenticationLog {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="login_event")
	private String loginEvent;
	
	@Column(name="signup_event")
	private String signupEvent;
	
	@Column(name="error_event")
	private String errorEvent;
	
	@Column(name="timestamp")
	private String timestamp;

	public String getLoginEvent() {
		return loginEvent;
	}

	public void setLoginEvent(String loginEvent) {
		this.loginEvent = loginEvent;
	}

	public String getSignupEvent() {
		return signupEvent;
	}

	public void setSignupEvent(String signupEvent) {
		this.signupEvent = signupEvent;
	}

	public String getErrorEvent() {
		return errorEvent;
	}

	public void setErrorEvent(String errorEvent) {
		this.errorEvent = errorEvent;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public int getId() {
		return id;
	}

	public AuthenticationLog(String loginEvent, String signupEvent, String errorEvent, String timestamp) {
		this.loginEvent = loginEvent;
		this.signupEvent = signupEvent;
		this.errorEvent = errorEvent;
		this.timestamp = timestamp;
	}
	
	public AuthenticationLog() {
		
	}
}
