package com.dumbcatan.websocket.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.dumbcatan.websocket.resource.WebsocketMessage;

@Controller
@CrossOrigin(origins = "*")
public class WSUserController {
	
	@MessageMapping("/user/{userId}/dataUpdate/for/game/{gameId}")
	@SendTo("/topic/user/{userId}/dataUpdate/for/game/{gameId}")
		public WebsocketMessage updateBoard(@DestinationVariable int userId, @DestinationVariable int gameId, WebsocketMessage message) {
		return message;
	}
}
