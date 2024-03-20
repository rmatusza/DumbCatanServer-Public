package com.dumbcatan.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dumbcatan.entity.Trade;
import com.dumbcatan.service.TradeServiceImpl;
import com.dumbcatan.service.UserGameServiceImpl;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api")
public class TradeController {
	
	@Autowired
	TradeServiceImpl tradeService;
	
	@Autowired
	UserGameServiceImpl userGameService;
	
	@PostMapping("/trade")
	public Trade createTrade(@RequestBody Trade trade) {
		int gameId = trade.getTradesGameId();
		int recipientId = trade.getTradesRecipientId();
		int senderId = trade.getTradesSenderId();
		try {
			Trade existingTrade = tradeService.findByTradesGameIdAndTradesSenderId(gameId, senderId);
			
			// DID NOT FIND EXISTING TRADE REQUEST BY THIS USER SO ALLOWED TO CREATE A NEW ONE 
			// NOTE: can only send one trade request at a time - makes the handling much easier and also makes 
			// sense in the context of the game 
			if(existingTrade == null) {
				tradeService.save(trade);
				trade.setStatus(200);
				trade.setMessage("Trade sent successfully");
				return trade;
			}
			// FOUND AN EXISTING TRADE REQUEST TO THIS USER
			else {
				trade.setStatus(403);
				trade.setMessage("You already have a trade pending with another user. Please wait until after they accept or decline that request before sending another.");
				return trade;
			}
		}
		catch(Exception e) {
			trade.setStatus(500);
			trade.setMessage("An error occurred when trying to send this trade request. Please try again.");
			return trade;
		}
	}
	
	@PutMapping("/trade")
	public Trade updateTrade(@RequestBody Trade trade) {
		try {
			trade.setTradeStatus(1);
			tradeService.save(trade);
			trade.setStatus(200);
			trade.setMessage("Trade accepted");
			return trade;
		}
		catch(Exception e) {
			trade.setStatus(500);
			trade.setMessage("Error occurred when attempting to accept trade. Please try again.");
			return trade;
		}
	}
	
	@GetMapping("/trade/is/pending")
	public ArrayList<Trade> getIsPendingTrade(@RequestParam int gameId, @RequestParam List<Integer> userIds) {
		ArrayList<Trade> pendingTrades = tradeService.findAllByTradesRecipientIdAndTradesGameId(userIds, gameId);
		return pendingTrades;
	}
	
	@GetMapping("/trade/requested/to/user/{recipientId}/for/game/{gameId}")
	public Trade getTradeRequest(@PathVariable int gameId, @PathVariable int recipientId) {
		Trade trade = null;
		try {
			return tradeService.findByTradesGameIdAndTradesRecipientId(gameId, recipientId);
		}
		catch(Exception e){
			trade = new Trade();
			trade.setStatus(500);
			trade.setMessage("There was an error when attempting to fetch trade requests. Please try again");
			return trade;
		}	
	}
	
	@GetMapping("/trade/sent/by/user/{senderId}/for/game/{gameId}/accepted")
	public Trade getTrade(@PathVariable int gameId, @PathVariable int senderId) {
		Trade trade = new Trade();

		try {
			Trade fetchedTrade = tradeService.findByTradesGameIdAndTradesSenderId(gameId, senderId);
			if(fetchedTrade != null && fetchedTrade.getTradeStatus() == 1) {
				fetchedTrade.setStatus(200);
				return fetchedTrade;
			}
			trade.setStatus(404);
			trade.setMessage("No accepted trades found.");
			return trade;
		}
		catch(Exception e) {
			trade.setStatus(500);
			trade.setMessage("There was an error when attempting to retrieve accepted trades. Please try again.");
			return trade;
		}
	}
	
	@DeleteMapping("/trade")
	public Trade deleteTrade(@RequestParam int tradeId) {
		Trade trade = tradeService.findById(tradeId);
		if(trade == null) {
			throw new RuntimeException("Trade with id " + tradeId + " not found");
		}
		
		try {
			tradeService.deleteById(tradeId);
			
			trade.setStatus(200);
			trade.setMessage("trade deleted");
			return trade;
		}
		catch(Exception e) {
			trade.setStatus(500);
			trade.setMessage("error occurred when attempting to delete trade");
			return trade;
		}
	}
}
