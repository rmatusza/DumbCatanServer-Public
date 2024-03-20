package com.dumbcatan.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dumbcatan.dao.TradeRepository;
import com.dumbcatan.entity.PlayerInfo;
import com.dumbcatan.entity.Trade;

@Service
public class TradeServiceImpl implements TradeService {
	
	@Autowired
	private TradeRepository tradeRepo;
	
	@Override
	public Trade save(Trade trade) {
		Trade savedTrade = new Trade();
		try {
			savedTrade = tradeRepo.save(trade);
			
			savedTrade.setStatus(200);
			savedTrade.setMessage("Trade saved");
			
			return savedTrade;
		}
		catch(Exception e) {
			savedTrade.setStatus(500);
			savedTrade.setMessage("An Error occurred when attempting to save trade");
			
			return savedTrade;
		}
	}

	@Override
	public void deleteById(int id) {
		tradeRepo.deleteById(id);
	}

	@Override
	public Trade findByTradesGameIdAndTradesRecipientId(int tradesGameId, int tradesRecipientId) {
		Optional<Trade> tradeOp = tradeRepo.findByTradesGameIdAndTradesRecipientId(tradesGameId, tradesRecipientId);
		Trade trade = new Trade();
		
		if (tradeOp.isPresent()) {
			trade = tradeOp.get();
			
			if(trade.getTradeStatus() != 1) {
				trade.setStatus(200);
				return trade;
			}
			else {
				trade.setStatus(404);
				return trade;
			}
		}
		
		trade.setStatus(404);
		return trade;
	}

	@Override
	public Trade findById(int id) {
		Optional<Trade> trade = tradeRepo.findById(id);
		
		if (trade.isPresent()) {
			return trade.get();
		} else {
			//throw new RuntimeException("No trade record found with game id: " + tradesGameId + " and recipient id: " + tradesRecipientId);
			return null;
		}
	}

	@Override
	public Trade findByTradesGameIdAndTradesSenderId(int tradesGameId, int tradesSenderId) {
		Optional<Trade> tradeInfo = tradeRepo.findByTradesGameIdAndTradesSenderId(tradesGameId, tradesSenderId);

		if (tradeInfo.isPresent()) {
			return tradeInfo.get();
		} else {
			return null;
		}
	}
	

	@Override
	public ArrayList<Trade> findAllByTradesRecipientIdAndTradesGameId(List<Integer> tradesRecipientIds,
			int tradesGameId) {
		ArrayList<Trade> activeTrades = null;
		ArrayList<Trade> returnTrades = new ArrayList<>();
		Trade trade = new Trade();
		
		try {
			activeTrades = tradeRepo.findAllByTradesRecipientIdInAndTradesGameId(tradesRecipientIds, tradesGameId);
			if(activeTrades.size() > 0) {

				for(Trade potTrade : activeTrades) {
//					System.out.println(potTrade.getTradeStatus());
					if(potTrade.getTradeStatus() != 1) {
						potTrade.setStatus(200);
						potTrade.setMessage("Found pending trades.");
						returnTrades.add(potTrade);
					}
				}

				if(returnTrades.size() > 0){
					return returnTrades;
				}
				else{
					trade.setStatus(404);
					trade.setMessage("Not trades found");
					returnTrades.add(trade);
					return returnTrades;
				}
			}
			else{
				trade.setStatus(404);
				trade.setMessage("Not trades found");
				returnTrades.add(trade);
				return returnTrades;
			}
		}
		catch(Exception e) {
			trade.setStatus(500);
			trade.setMessage("There was an error when trying to fetch active trades.");
			returnTrades.add(trade);
			return returnTrades;
		}
	}
}
