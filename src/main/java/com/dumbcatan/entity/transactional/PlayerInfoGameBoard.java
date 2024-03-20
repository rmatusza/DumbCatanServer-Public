package com.dumbcatan.entity.transactional;

import java.util.ArrayList;

import com.dumbcatan.entity.GameBoard;
import com.dumbcatan.entity.PlayerInfo;
import com.dumbcatan.entity.http.ResponseEntityHelper;

public class PlayerInfoGameBoard extends ResponseEntityHelper {
	
	private GameBoard gameBoard;
	private PlayerInfo playerInfo;
	private ArrayList<PlayerInfo> playerInfos;
	
	public ArrayList<PlayerInfo> getPlayerInfos() {
		return playerInfos;
	}

	public void setPlayerInfos(ArrayList<PlayerInfo> playerInfos) {
		this.playerInfos = playerInfos;
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
	
	public PlayerInfoGameBoard() {
			
	}

	public PlayerInfoGameBoard(GameBoard gameBoard, ArrayList<PlayerInfo> playerInfos, PlayerInfo playerInfo) {
		this.gameBoard = gameBoard;
		this.playerInfos = playerInfos;
		this.playerInfo = playerInfo;
	}
}
