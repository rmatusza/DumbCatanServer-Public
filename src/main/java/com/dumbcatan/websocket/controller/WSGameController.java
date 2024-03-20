package com.dumbcatan.websocket.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.dumbcatan.websocket.resource.WebsocketMessage;

@Controller
@CrossOrigin(origins = "*")
public class WSGameController {
	
	@MessageMapping("/game/{gameId}/board/update")
	@SendTo("/topic/game/{gameId}/updates")
		public WebsocketMessage updateBoard(@DestinationVariable int gameId, WebsocketMessage message) {
		return message;
	}
	
	@MessageMapping("/game/{gameId}/update")
	@SendTo("/topic/game/{gameId}/update")
		public WebsocketMessage updateGame(@DestinationVariable int gameId, WebsocketMessage message) {
		return message;
	}
	
	@MessageMapping("/game/{gameId}/gamePhase/update")
	@SendTo("/topic/game/{gameId}/gamePhase-updates")
		public WebsocketMessage updateGamePhase(@DestinationVariable int gameId, WebsocketMessage message) {
		return message;
	}
	
	@MessageMapping("/game/{gameId}/trade/request/to/{recipientId}")
	@SendTo("/topic/game/{gameId}/trades/requests/to/{recipientId}")
		public WebsocketMessage sendTradeRequest(@DestinationVariable int recipientId, WebsocketMessage message) {
		return message;
	}
	
	@MessageMapping("/game/{gameId}/trade/request/from/{senderId}/to/{recipientId}/accepted")
	@SendTo("/topic/game/{gameId}/trades/requests/from/{senderId}/accepted")
		public WebsocketMessage acceptTradeRequest(@DestinationVariable int recipientId, @DestinationVariable int senderId, WebsocketMessage message) {
		return message;
	}
	
	@MessageMapping("/game/{gameId}/diceRoll")
	@SendTo("/topic/game/{gameId}/diceRoll")
		public WebsocketMessage diceRoll(@DestinationVariable int gameId,  WebsocketMessage message) {
		return message;
	}
}
