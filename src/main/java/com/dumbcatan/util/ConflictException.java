package com.dumbcatan.util;

import org.springframework.http.HttpStatus;

public class ConflictException extends RuntimeException{

	private String errorMessage = "User is already logged in";
	private int statusCode = 409;
	private HttpStatus status = HttpStatus.CONFLICT;
	
	public String getErrorMessage() {
		return errorMessage;
	}


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public int getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}


	public HttpStatus getStatusType() {
		return status;
	}


	public void setStatusType(HttpStatus statusType) {
		this.status = status;
	}


	public ConflictException() {
	}

}
