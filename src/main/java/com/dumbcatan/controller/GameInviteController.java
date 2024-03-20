package com.dumbcatan.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dumbcatan.entity.Game;
import com.dumbcatan.entity.GameInvite;
import com.dumbcatan.entity.User;
import com.dumbcatan.entity.UserGame;
import com.dumbcatan.entity.http.GameDataObj;
import com.dumbcatan.service.GameInviteServiceImpl;
import com.dumbcatan.service.GameServiceImpl;
import com.dumbcatan.service.MyUserDetailsService;
import com.dumbcatan.service.UserGameServiceImpl;
import com.dumbcatan.service.UserServiceImpl;
import com.dumbcatan.util.JwtUtil;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class GameInviteController {

	@Autowired
	private GameServiceImpl gameService;

	@Autowired
	private UserServiceImpl US;

	@Autowired
	private GameInviteServiceImpl gameInviteService;
	
	@Autowired
	private UserGameServiceImpl UGS;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@PostMapping("/invite")
	public GameInvite sendGameInvite(@RequestBody GameInvite newInvite, @RequestParam String recipient_username)
			throws Exception {

		Game game = null;
		User recipient = null;
		UserGame UG = null;
		int gameId = newInvite.getGameId();
		GameInvite genericInvite = new GameInvite();

		
		// 1: check to see if game number exists
		try {
			game = gameService.findById(gameId);
			if(game == null){
				genericInvite.setStatus(404);
				genericInvite.setMessage("Did not find a game with id: " + gameId);
				return genericInvite;
			}
		}
		catch(RuntimeException RE) {
			genericInvite.setStatus(500);
			genericInvite.setMessage("There was an error when trying to create an invite for game: " + gameId);
			return genericInvite;
		}
		
		// 1: check to see if user has authorization to send invites. if the player list
		// is not full ensure that the sender id is the same as the owner id because
		// only
		// the owner of the game can send invites
		if (game.getOwnerId() != newInvite.getSenderId()) {
			//throw new Exception("Only the owner of game " + invite.getGameId() + " can send an invite");
			genericInvite.setStatus(401);
			genericInvite.setMessage("Only the owner of game " + newInvite.getGameId() + " can send invites");
			return genericInvite;
		};
		
		// 2: check to see if player list is already full. query the games table
		// using the game id and see if any players can be invited or if the list is
		// full
		int players = game.getPlayerSize();

		if (players == 4) {
			//throw new Exception("The player list for game " + gameId + " is already full");
			genericInvite.setStatus(403);
			genericInvite.setMessage("The player list for game " + gameId + " is already full");
			return genericInvite;
		}
		
		// 3: check to see if user has already accepted the invite
		// fetch user record using user name
		// extract id from user record and use to search user games
		// if record found = user is already a member and can't receive an invite
		// if record not found = user is NOT already a member and can receive an invite - if one wasn't already sent
		try {
			recipient = US.findByUsername(recipient_username);
		}
		catch(RuntimeException RE) {
			genericInvite.setStatus(404);
			genericInvite.setMessage("User not found");
			return genericInvite;
		}
		
		UG = UGS.findByUserGamesUserIdAndUserGamesGameId(recipient.getUserId(), gameId);
		
		if(UG != null) {
			genericInvite.setStatus(403);
			genericInvite.setMessage(recipient_username + " is already a member of game " + gameId);
			return genericInvite;
		}
		
		// 4: check to see if user has already been invited. check to see if
		// there is a record containing the recipient id AND the game id. if
		// one exists then this recipient has already been invited
		int recipientId = recipient.getUserId();
		GameInvite existingInvite = gameInviteService.findByRecipientIdAndGameId(recipientId, gameId);

		if (existingInvite != null) {
			//throw new Exception("You have already sent an invite to " + recipient_username + " for game " + gameId);
			genericInvite.setStatus(403);
			genericInvite.setMessage("You have already sent an invite to " + recipient_username + " for game " + gameId);
			return genericInvite;
		}
		
		// 5: send the invite
		newInvite.setRecipientId(recipientId);
		gameInviteService.save(newInvite);
		
		newInvite.setStatus(200);
		newInvite.setMessage("Invite Created");
		return newInvite;
	}

	@GetMapping("/invites/for/{userId}")
	public List<Game> getGameInvites(@PathVariable int userId) {
		List<Game> games = new ArrayList<>();
		List<GameInvite> invites = new ArrayList<>();
		List<Integer> ids = new ArrayList<>();
		
		try {
			invites = gameInviteService.findByRecipientId(userId);
			
			if(invites.size() == 0) {
				Game game = new Game();
				
				game.setStatus(404);
				game.setMessage("No invites found.");
				games.add(game);

				return games;
			}
			
			for(GameInvite invite : invites) {
				int gameId = invite.getGameId();
				ids.add(gameId);
			}
			
			games = gameService.findByGameIdIn(ids);
			
			Game firstGame = games.get(0);
			
			firstGame.setStatus(200);
			firstGame.setMessage("Invites found and fetched successfully.");
		
			return games;
		}
		catch(Exception e){
			Game game = new Game();
			
			game.setStatus(500);
			game.setMessage("There was an error when attempting to fetch game invites. Please try again.");
			games.add(game);
			
			return games;
		}
	}
	
	@PostMapping("/invites/for/game/{gameId}/to/user/{userId}/accept")
	public Game acceptGameInvite(@RequestBody GameDataObj gameObj, @PathVariable int gameId, @PathVariable int userId) {
		Game gameToUpdate = gameService.findById(gameId);
		GameInvite gameInvite = gameInviteService.findByRecipientIdAndGameId(userId, gameId);
		
		int gameInviteId = gameInvite.getGameInviteId();
		
		int playerSize = gameToUpdate.getPlayerSize();
		gameToUpdate.setPlayerSize(playerSize += 1);
		gameToUpdate.setPlayers(gameObj.getPlayers());
		
		try {
			gameService.save(gameToUpdate);
		}
		catch(Exception e) {
			gameToUpdate.setStatus(500);
			gameToUpdate.setMessage("There was an error when trying to add new player to game: " + e.getMessage());
			return gameToUpdate;
		}
		
		try {
			gameInviteService.deleteById(gameInviteId);
		}
		catch(Exception e) {
			gameToUpdate.setStatus(500);
			gameToUpdate.setMessage("There was an error when trying to delete the accepted invite: " + e.getMessage());
			return gameToUpdate;
		}
		
		gameToUpdate.setStatus(200);
		gameToUpdate.setMessage("Invite accepted");
		return gameToUpdate;
	}

	
	
	@DeleteMapping("/invites/decline")
	public void declineGameInvite(@RequestParam int gameId, @RequestParam int recipientId) {
		gameInviteService.deleteByRecipientIdAndGameId(recipientId, gameId);
	}
	
}
