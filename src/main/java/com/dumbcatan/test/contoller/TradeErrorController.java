package com.dumbcatan.test.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dumbcatan.entity.Trade;
import com.dumbcatan.service.TradeServiceImpl;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api")
public class TradeErrorController {
	
	@Autowired
	TradeServiceImpl tradeService;

	@PostMapping("/trade/error")
	public Trade createTradeError(@RequestBody Trade trade) {
		
		String existingTrade = "A trade has already been sent to this user.";
		int existingTradeCode = 403;
		
		String dbError = "An error occurred when trying to send this trade request. Please try again.";
		int dbErrorCode = 500;
		
//		trade.setStatus(dbErrorCode);
//		trade.setMessage(dbError);
		
		trade.setStatus(existingTradeCode);
		trade.setMessage(existingTrade);
		
		return trade;
	}
}
