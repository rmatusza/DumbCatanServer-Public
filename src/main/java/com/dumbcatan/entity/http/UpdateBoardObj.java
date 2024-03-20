package com.dumbcatan.entity.http;

// helper class for updating the game board field of the Game entity

// creates a java object out of a client request that holds the game board
// and game id
public class UpdateBoardObj {
	
	private int gameId;
	
	private String boardStructures;

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getBoardStructures() {
		return boardStructures;
	}

	public void setBoardStructures(String boardStructures) {
		this.boardStructures = boardStructures;
	}
	
	public UpdateBoardObj() {}
	
	public UpdateBoardObj(int gameId, String boardStructures) {
		this.gameId = gameId;
		this.boardStructures = boardStructures;
	}

	@Override
	public String toString() {
		return "UpdateData [gameId=" + gameId + ", boardStructures=" + boardStructures + "]";
	}
	
}
