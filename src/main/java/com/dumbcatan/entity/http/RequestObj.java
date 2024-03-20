package com.dumbcatan.entity.http;


// helper class that acts as a general request object for client http request
public class RequestObj {
	
	private String currPlayerUsername;
	private String gameBoard;
	private int turnPhaseIdx;

	public String getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(String gameBoard) {
		this.gameBoard = gameBoard;
	}

	public int getTurnPhaseIdx() {
		return turnPhaseIdx;
	}

	public void setTurnPhaseIdx(int turnPhaseIdx) {
		this.turnPhaseIdx = turnPhaseIdx;
	}

	public String getCurrPlayerUsername() {
		return currPlayerUsername;
	}

	public void setCurrPlayerUsername(String currPlayerUsername) {
		this.currPlayerUsername = currPlayerUsername;
	}

	public RequestObj(String currPlayerUsername, int turnPhaseIdx, String gameBoard) {
		this.currPlayerUsername = currPlayerUsername;
		this.turnPhaseIdx = turnPhaseIdx;
		this.gameBoard = gameBoard;
	}
	
	public RequestObj() {
		
	}
	
}
