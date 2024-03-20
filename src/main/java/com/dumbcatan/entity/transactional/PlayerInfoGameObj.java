package com.dumbcatan.entity.transactional;

import java.util.ArrayList;

import com.dumbcatan.entity.Game;
import com.dumbcatan.entity.PlayerInfo;
import com.dumbcatan.entity.http.ResponseEntityHelper;

public class PlayerInfoGameObj extends ResponseEntityHelper{
	
	private Game game;
	private PlayerInfo playerInfo;
	private ArrayList<PlayerInfo> playerInfos;
	
	public ArrayList<PlayerInfo> getPlayerInfos() {
		return playerInfos;
	}

	public void setPlayerInfos(ArrayList<PlayerInfo> playerInfos) {
		this.playerInfos = playerInfos;
	}


	public Game getGame() {
		return game;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public PlayerInfo getPlayerInfo() {
		return playerInfo;
	}
	
	public void setPlayerInfo(PlayerInfo playerInfo) {
		this.playerInfo = playerInfo;
	}
	
	public PlayerInfoGameObj(Game game, PlayerInfo playerInfo, ArrayList<PlayerInfo> playerInfos) {
		this.game = game;
		this.playerInfo = playerInfo;
		this.playerInfos = playerInfos;
	}
	
	public PlayerInfoGameObj() {
		
	}
}
