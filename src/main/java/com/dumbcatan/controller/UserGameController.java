package com.dumbcatan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dumbcatan.entity.User;
import com.dumbcatan.entity.UserGame;
import com.dumbcatan.service.UserGameServiceImpl;
import com.dumbcatan.service.UserServiceImpl;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api")
public class UserGameController {
	
	@Autowired
	private UserGameServiceImpl UGS;
	
	@Autowired
	private UserServiceImpl US;
	
	@PostMapping("/userGames/create")
	public UserGame createUserGameEntry(@RequestBody UserGame UG) {		
		try {
			UGS.save(UG);
		}
		catch(Exception e) {
			UG.setStatus(500);
			UG.setMessage("Failed to save UserGame record: " + e.getMessage());
			return UG;
		}
		
		UG.setStatus(200);
		UG.setMessage("UserGame record created");
		
		return UG;
		
//		UG.setStatus(500);
//		UG.setMessage("Failed to save UserGame record: ");
//		return UG;
	}
	
	@GetMapping("/userGames/userId/{userId}/game/{gameId}")
	public UserGame getUserGameEntry(@PathVariable int userId, @PathVariable int gameId) {
		UserGame UG = UGS.findByUserGamesUserIdAndUserGamesGameId(userId, gameId);
		
		if(UG == null) {
			UG.setStatus(404);
			UG.setMessage("User not a member of this game");
			return UG;
		}
		else {
			UG.setStatus(200);
			UG.setMessage("User is a member of this game");
			return UG;
		}
	}
	
	@GetMapping("/userGames/username/{username}/game/{gameId}")
	public UserGame getUserGameEntryByUsername(@PathVariable String username, @PathVariable int gameId) {
		
		// NOTE: this method is updating 2 db tables - users and user games
		// -> not sure if that is best practice
		User user = null;
		UserGame UG = new UserGame();
		
		try {
			user = US.findByUsername(username);
		}
		catch(RuntimeException RE) {
			UG.setStatus(404);
			UG.setMessage("User not found");
			return UG;
		}
		
		UG = UGS.findByUserGamesUserIdAndUserGamesGameId(user.getUserId(), gameId);
		
		if(UG == null) {
			UG.setStatus(404);
			UG.setMessage("User not a member of this game");
			return UG;
		}
		else {
			UG.setStatus(200);
			UG.setMessage("User is a member of this game");
			return UG;
		}
	}
	
}
