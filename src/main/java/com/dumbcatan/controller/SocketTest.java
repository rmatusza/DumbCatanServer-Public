package com.dumbcatan.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.dumbcatan.websocket.resource.Greeting;

@CrossOrigin(origins = "*")
@Controller
public class SocketTest {

//	@MessageMapping("/hello")
//	@SendTo("/topic/greetings")
//	public Greeting greeting(HelloMessage message) {
//		return new Greeting("Hello, " + message.getName());
//	}
}


// react websocket: https://www.youtube.com/watch?v=o_IjEDAuo8Y&t=6s
// spring websocket: https://www.youtube.com/watch?v=n6ZqOwreFTA&t=119s