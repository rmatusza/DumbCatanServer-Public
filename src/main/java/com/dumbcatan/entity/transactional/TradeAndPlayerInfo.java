package com.dumbcatan.entity.transactional;

import com.dumbcatan.entity.PlayerInfo;
import com.dumbcatan.entity.Trade;
import com.dumbcatan.entity.http.ResponseEntityHelper;

public class TradeAndPlayerInfo extends ResponseEntityHelper{
	
	private Trade trade;
	private PlayerInfo playerInfo;
	
	public Trade getTrade() {
		return trade;
	}
	
	public void setTrade(Trade trade) {
		this.trade = trade;
	}
	
	public PlayerInfo getPlayerInfo() {
		return playerInfo;
	}
	
	public void setPlayerInfo(PlayerInfo playerInfo) {
		this.playerInfo = playerInfo;
	}
	
	public TradeAndPlayerInfo() {
		
	}
	
	public TradeAndPlayerInfo(Trade trade, PlayerInfo playerInfo) {
		this.trade = trade;
		this.playerInfo = playerInfo;
	}
}
