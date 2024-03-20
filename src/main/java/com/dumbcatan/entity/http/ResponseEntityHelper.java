package com.dumbcatan.entity.http;

public class ResponseEntityHelper {
	
	public int status;
	public String message;
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public ResponseEntityHelper(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	public ResponseEntityHelper() {
		
	}
}
