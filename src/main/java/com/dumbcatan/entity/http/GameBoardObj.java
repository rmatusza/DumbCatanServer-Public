package com.dumbcatan.entity.http;

// helper entity class that represents only the game board field of a Game record
// allows to cut back on http request/response sizes when only needing to work
// with the game board

public class GameBoardObj {
	
	private String gameBoard;

	public String getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(String gameBoard) {
		this.gameBoard = gameBoard;
	}

	public GameBoardObj(String gameBoard) {
		this.gameBoard = gameBoard;
	}
	
	public GameBoardObj() {
		
	}
	
}
