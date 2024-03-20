package com.dumbcatan.service;

import java.util.ArrayList;
import java.util.List;

import com.dumbcatan.entity.PlayerInfo;
import com.dumbcatan.entity.Trade;

public interface TradeService {
	
	public Trade save(Trade passenger);
	
	public void deleteById(int id);
	
	public Trade findById(int id);
	
	public Trade findByTradesGameIdAndTradesRecipientId(int tradesGameId, int tradesRecipientId);
	
	public Trade findByTradesGameIdAndTradesSenderId(int tradesGameId, int tradesSenderId);
	
//	public Trade findByTradesGameIdAndTradesSenderIdAndTradesRecipientId(int tradesGameId, int tradesSenderId, int tradesRecipientId);
	
	public ArrayList<Trade> findAllByTradesRecipientIdAndTradesGameId(List<Integer> tradesRecipientIds, int tradesGameId);
	
}
