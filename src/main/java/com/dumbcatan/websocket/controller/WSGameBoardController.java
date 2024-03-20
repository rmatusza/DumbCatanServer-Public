package com.dumbcatan.websocket.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.dumbcatan.websocket.resource.WebsocketMessage;

@Controller
@CrossOrigin(origins = "*")
public class WSGameBoardController {
	
	@MessageMapping("/game/{gameId}/gameBoard/update")
	@SendTo("/topic/game/{gameId}/gameBoard")
		public WebsocketMessage updateGame(@DestinationVariable int gameId, WebsocketMessage message) {
		return message;
	}
}
