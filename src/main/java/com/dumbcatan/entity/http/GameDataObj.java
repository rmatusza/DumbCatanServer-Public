package com.dumbcatan.entity.http;

// helper entity class that represents a game record without the game board data
// allows to cut back on the data size of requests/responses when only non-board game
// data is needed

public class GameDataObj {
		
	private String devCards;
	private int currentPlayerIdx;
	private String lastUpdated;
	private int playerSize;
	private int initialGamePhase = 0;
	private int mainGamePhase = 0;
	private int gameOver = 0;
	private String winnerUsername = null;
	private String awards;
	private String initialGameInstructions;
	private int currDiceRoll;
	private String currPlayerUsername;
	private int currTurnPhaseIdx;
	private String players;
	
	public String getPlayers() {
		return players;
	}

	public void setPlayers(String players) {
		this.players = players;
	}

	public String getDevCards() {
		return devCards;
	}
	
	public void setDevCards(String devCards) {
		this.devCards = devCards;
	}
	
	public int getCurrentPlayerIdx() {
		return currentPlayerIdx;
	}
	
	public void setCurrentPlayerIdx(int currentPlayerIdx) {
		this.currentPlayerIdx = currentPlayerIdx;
	}
	
	public String getLastUpdated() {
		return lastUpdated;
	}
	
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	public int getPlayerSize() {
		return playerSize;
	}
	
	public void setPlayerSize(int playerSize) {
		this.playerSize = playerSize;
	}
	
	public int getInitialGamePhase() {
		return initialGamePhase;
	}
	
	public void setInitialGamePhase(int initialGamePhase) {
		this.initialGamePhase = initialGamePhase;
	}
	
	public int getMainGamePhase() {
		return mainGamePhase;
	}
	
	public void setMainGamePhase(int mainGamePhase) {
		this.mainGamePhase = mainGamePhase;
	}
	
	public int getGameOver() {
		return gameOver;
	}
	
	public void setGameOver(int gameOver) {
		this.gameOver = gameOver;
	}
	
	public String getWinnerUsername() {
		return winnerUsername;
	}
	
	public void setWinnerUsername(String winnerUsername) {
		this.winnerUsername = winnerUsername;
	}
	
	public String getAwards() {
		return awards;
	}
	
	public void setAwards(String awards) {
		this.awards = awards;
	}
	
	public String getInitialGameInstructions() {
		return initialGameInstructions;
	}
	
	public void setInitialGameInstructions(String initialGameInstructions) {
		this.initialGameInstructions = initialGameInstructions;
	}
	
	public int getCurrDiceRoll() {
		return currDiceRoll;
	}
	
	public void setCurrDiceRoll(int currDiceRoll) {
		this.currDiceRoll = currDiceRoll;
	}
	
	public String getCurrPlayerUsername() {
		return currPlayerUsername;
	}
	
	public void setCurrPlayerUsername(String currPlayerUsername) {
		this.currPlayerUsername = currPlayerUsername;
	}
	
	public int getCurrTurnPhaseIdx() {
		return currTurnPhaseIdx;
	}
	
	public void setCurrTurnPhaseIdx(int currTurnPhaseIdx) {
		this.currTurnPhaseIdx = currTurnPhaseIdx;
	}
	
	public GameDataObj(String devCards, int currentPlayerIdx, String lastUpdated, int playerSize,
			int initialGamePhase, int mainGamePhase, int gameOver, String winnerUsername, String awards,
			String initialGameInstructions, int currDiceRoll, String currPlayerUsername, int currTurnPhaseIdx, String players) {
		this.devCards = devCards;
		this.currentPlayerIdx = currentPlayerIdx;
		this.lastUpdated = lastUpdated;
		this.playerSize = playerSize;
		this.initialGamePhase = initialGamePhase;
		this.mainGamePhase = mainGamePhase;
		this.gameOver = gameOver;
		this.winnerUsername = winnerUsername;
		this.awards = awards;
		this.initialGameInstructions = initialGameInstructions;
		this.currDiceRoll = currDiceRoll;
		this.currPlayerUsername = currPlayerUsername;
		this.currTurnPhaseIdx = currTurnPhaseIdx;
		this.players = players;
	}
	
	public GameDataObj() {
		
	}

}
