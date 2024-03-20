package com.dumbcatan.entity.transactional;

import org.springframework.stereotype.Component;

import com.dumbcatan.entity.Game;
import com.dumbcatan.entity.GameBoard;
import com.dumbcatan.entity.PlayerInfo;
import com.dumbcatan.entity.http.ResponseEntityHelper;

@Component
public class NewGameDataObj extends ResponseEntityHelper{
	
	private Game game;
	private GameBoard gameBoard;
	private PlayerInfo playerInfo;
	
	public Game getGame() {
		return game;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public GameBoard getGameBoard() {
		return gameBoard;
	}
	
	public void setGameBoard(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
	}
	
	public PlayerInfo getPlayerInfo() {
		return playerInfo;
	}
	
	public void setPlayerInfo(PlayerInfo playerInfo) {
		this.playerInfo = playerInfo;
	}
	
	
	public NewGameDataObj() {
	}
	
	public NewGameDataObj(Game game, GameBoard gameBoard, PlayerInfo playerInfo) {
		this.game = game;
		this.gameBoard = gameBoard;
		this.playerInfo = playerInfo;
	}
}
