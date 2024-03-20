package com.dumbcatan.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dumbcatan.entity.Trade;

public interface TradeRepository extends JpaRepository<Trade, Integer> {
	
	Optional<Trade> findByTradesGameIdAndTradesRecipientId(int tradesGameId, int tradesRecipientId);
	
	Optional<Trade> findByTradesGameIdAndTradesSenderId(int tradesGameId, int tradesSenderId);
	
//	Optional<Trade> findByTradesGameIdAndTradesSenderIdAndTradesRecipientId(int tradesGameId, int tradesSenderId, int tradesRecipientId);
	
	ArrayList<Trade> findAllByTradesRecipientIdInAndTradesGameId(List<Integer> tradesRecipientIds, int tradesGameId);
}
