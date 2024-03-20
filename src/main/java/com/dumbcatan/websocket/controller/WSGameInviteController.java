package com.dumbcatan.websocket.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.dumbcatan.websocket.resource.WebsocketMessage;

@Controller
@CrossOrigin(origins = "*")
public class WSGameInviteController {
	
	
	@MessageMapping("/invite/{recipient}")
	@SendTo("/topic/gameInvites/{recipient}")
		public WebsocketMessage invite(@DestinationVariable String recipient, WebsocketMessage message) {
		return message;
	}
	
//	@MessageMapping("/invite/{gameId}/games-list/accept")
//	@SendTo("/topic/gameOwner/{ownerId}/player-list-update/games-list-page")
//		public WebsocketMessage acceptInviteGamesList(@DestinationVariable int gameId, @DestinationVariable int ownerId, WebsocketMessage message) {
////		System.out.println(message);
//		return message;
//	}
	
	@MessageMapping("/invite/{gameId}/game-space/accept")
	@SendTo("/topic/game/{gameId}/player-list-update/game-space-page")
		public WebsocketMessage acceptInviteGameSpace(@DestinationVariable int gameId, WebsocketMessage message) {
//		System.out.println(message);
		return message;
	}
}
