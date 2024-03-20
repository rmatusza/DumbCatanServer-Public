package com.dumbcatan.entity.http;

import org.springframework.stereotype.Component;

// helper object that serves as a general http response object with fields
// that typically need to be returned to the client such as "operation successful"
// error messages and the game board

// REFACTOR NOTE: get rid of game board fields
// -> there is already a GameBoardObject class in the same package that
// handles that
@Component
public class ResponseObj {
	
	private boolean succeeded;
	private String message;
	private boolean isOSCP;
	private String gameBoard;
	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(String gameBoard) {
		this.gameBoard = gameBoard;
	}

	public boolean isSucceeded() {
		return succeeded;
	}

	public void setOSCP(boolean isOSCP) {
		this.isOSCP = isOSCP;
	}

	public boolean getIsOSCP() {
		return isOSCP;
	}

	public void setIsOSCP(boolean isOSCP) {
		this.isOSCP = isOSCP;
	}

	public boolean getIsSucceeded() {
		return succeeded;
	}
	
	public void setSucceeded(boolean succeeded) {
		this.succeeded = succeeded;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public ResponseObj() {
		
	}

	public ResponseObj(boolean succeeded, String message) {
		this.succeeded = succeeded;
		this.message = message;
	}
}
