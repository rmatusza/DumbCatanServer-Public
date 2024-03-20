package com.dumbcatan.entity;

import java.util.List;

import javax.persistence.*;

import com.dumbcatan.entity.http.GameDataObj;
import com.dumbcatan.entity.http.ResponseEntityHelper;


@Entity
@Table(name="games")
public class Game extends ResponseEntityHelper{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="game_id")
	private int gameId;
	
	@Column(name="owner_id")
	private int ownerId;
	
	@Column(name="players")
	private String players;
	
	@Column(name="dev_cards")
	private String devCards;
	
	@Column(name="ports")
	private String ports;
	
	@Column(name="current_player_idx")
	private int currentPlayerIdx;
	
	@Column(name="last_updated")
	private String lastUpdated;
	
	@Column(name="player_size")
	private int playerSize;
	
	@Column(name="initial_game_phase")
	private int initialGamePhase = 0;
	
	@Column(name="main_game_phase")
	private int mainGamePhase = 0;
	
	@Column(name="game_over")
	private int gameOver = 0;
	
	@Column(name="winner_username")
	private String winnerUsername = null;
	
	@Column(name="awards")
	private String awards;
	
	@Column(name="initial_game_instructions")
	private String initialGameInstructions;
	
	@Column(name="curr_dice_roll")
	private int currDiceRoll;
	
	@Column(name="curr_player_username")
	private String currPlayerUsername;
	
	@Column(name="curr_turn_phase_idx")
	private int currTurnPhaseIdx;
	
	@OneToMany(mappedBy="userGamesGameId", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<UserGame> userGames;
	
	@OneToMany(mappedBy="playerInfoGameId", cascade = CascadeType.REMOVE, orphanRemoval=true)
	private List<PlayerInfo> playerInfos;
	
//	@OneToMany(mappedBy="gameBoardGameId", cascade = CascadeType.REMOVE, orphanRemoval=true)
//	private List<GameBoard> gameBoards;

	@OneToOne(mappedBy = "game", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private GameBoard gameBoard;

	public GameBoard getGameBoard(){
		return gameBoard;
	}

	public void setGameBoard(GameBoard gameBoard){
		this.gameBoard = gameBoard;
	}
	public List<UserGame> getUserGames() {
		return userGames;
	}

	public void setUserGames(List<UserGame> userGames) {
		this.userGames = userGames;
	}

//	public List<GameBoard> getGameBoards() {
//		return gameBoards;
//	}
//
//	public void setGameBoards(List<GameBoard> gameBoards) {
//		this.gameBoards = gameBoards;
//	}

	public List<PlayerInfo> getPlayerInfos() {
		return playerInfos;
	}

	public void setPlayerInfos(List<PlayerInfo> playerInfos) {
		this.playerInfos = playerInfos;
	}

	public int getCurrTurnPhaseIdx() {
		return currTurnPhaseIdx;
	}

	public void setCurrTurnPhaseIdx(int currTurnPhaseIdx) {
		this.currTurnPhaseIdx = currTurnPhaseIdx;
	}

	public String getCurrPlayerUsername() {
		return currPlayerUsername;
	}

	public void setCurrPlayerUsername(String currPlayerUsername) {
		this.currPlayerUsername = currPlayerUsername;
	}

	public int getCurrDiceRoll() {
		return currDiceRoll;
	}

	public void setCurrDiceRoll(int currDiceRoll) {
		this.currDiceRoll = currDiceRoll;
	}

	public String getInitialGameInstructions() {
		return initialGameInstructions;
	}

	public void setInitialGameInstructions(String initialGameInstructions) {
		this.initialGameInstructions = initialGameInstructions;
	}

	public String getWinnerUsername() {
		return winnerUsername;
	}

	public void setWinnerUsername(String winnerUsername) {
		this.winnerUsername = winnerUsername;
	}

	public int getGameOver() {
		return gameOver;
	}

	public void setGameOver(int gameOver) {
		this.gameOver = gameOver;
	}

	public String getAwards() {
		return awards;
	}

	public void setAwards(String awards) {
		this.awards = awards;
	}

	public String getDevCards() {
		return devCards;
	}

	public void setDevCards(String devCards) {
		this.devCards = devCards;
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

	public int getCurrentPlayerIdx() {
		return currentPlayerIdx;
	}

	public void setCurrentPlayerIdx(int currentPlayerIdx) {
		this.currentPlayerIdx = currentPlayerIdx;
	}

	public int getPlayerSize() {
		return playerSize;
	}

	public void setPlayerSize(int playerSize) {
		this.playerSize = playerSize;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getPorts() {
		return ports;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public void setPorts(String ports) {
		this.ports = ports;
	}

	public String getPlayers() {
		return players;
	}

	public void setPlayers(String players) {
		this.players = players;
	}

	public int getCurrentPlayerId() {
		return currentPlayerIdx;
	}

	public void setCurrentPlayerId(int currentPlayerId) {
		this.currentPlayerIdx = currentPlayerId;
	}

	public int getGameId() {
		return gameId;
	}
	
	public void updateGameData(GameDataObj GDR) {
		this.setDevCards(GDR.getDevCards());
		this.setCurrentPlayerIdx(GDR.getCurrentPlayerIdx());
		this.setLastUpdated(GDR.getLastUpdated());
		this.setPlayerSize(GDR.getPlayerSize());
		this.setInitialGamePhase(GDR.getInitialGamePhase());
		this.setMainGamePhase(GDR.getMainGamePhase());
		this.setGameOver(GDR.getGameOver());
		this.setWinnerUsername(GDR.getWinnerUsername());
		this.setAwards(GDR.getAwards());
		this.setInitialGameInstructions(GDR.getInitialGameInstructions());
		this.setCurrDiceRoll(GDR.getCurrDiceRoll());
		this.setCurrPlayerUsername(GDR.getCurrPlayerUsername());
		this.setCurrTurnPhaseIdx(GDR.getCurrTurnPhaseIdx());
	}
	
	public Game cloneWithoutBoardStructures() {
		Game gameClone = new Game();
		
		gameClone.setGameId(this.getGameId());
		gameClone.setDevCards(this.getDevCards());
		gameClone.setCurrentPlayerIdx(this.getCurrentPlayerIdx());
		gameClone.setLastUpdated(this.getLastUpdated());
		gameClone.setPlayerSize(this.getPlayerSize());
		gameClone.setInitialGamePhase(this.getInitialGamePhase());
		gameClone.setMainGamePhase(this.getMainGamePhase());
		gameClone.setGameOver(this.getGameOver());
		gameClone.setWinnerUsername(this.getWinnerUsername());
		gameClone.setAwards(this.getAwards());
		gameClone.setInitialGameInstructions(this.getInitialGameInstructions());
		gameClone.setCurrDiceRoll(this.getCurrDiceRoll());
		gameClone.setCurrPlayerUsername(this.getCurrPlayerUsername());
		gameClone.setCurrTurnPhaseIdx(this.getCurrTurnPhaseIdx());
		gameClone.setOwnerId(this.getOwnerId());
		gameClone.setPlayers(this.getPlayers());
		gameClone.setPorts(this.getPorts());
		
		return gameClone;
	}

	public Game(int gameId, int ownerId, String players, String devCards,
			 	String ports, int currentPlayerIdx, String lastUpdated, int playerSize, String awards, String initialGameInstructions, int currTurnPhaseIdx) {
		this.gameId = gameId;
		this.ownerId = ownerId;
		this.players = players;
		this.devCards = devCards;
		this.ports = ports;
		this.currentPlayerIdx = currentPlayerIdx;
		this.lastUpdated = lastUpdated;
		this.playerSize = playerSize;
		this.awards = awards;	
		this.initialGameInstructions = initialGameInstructions;
		this.currTurnPhaseIdx = currTurnPhaseIdx;
	}

	public Game() {

	}
}
