package com.dumbcatan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dumbcatan.entity.Game;
import com.dumbcatan.entity.PlayerInfo;
import com.dumbcatan.entity.http.GameDataObj;
import com.dumbcatan.entity.http.ResponseObj;
import com.dumbcatan.service.GameServiceImpl;
import com.dumbcatan.service.MyUserDetailsService;
import com.dumbcatan.service.PlayerInfoServiceImpl;
import com.dumbcatan.service.UserGameServiceImpl;
import com.dumbcatan.util.JwtUtil;
import com.dumbcatan.util.ObtainDate;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api")
public class GameController {
	
	@Autowired
	private GameServiceImpl gameService;
	
	@Autowired
	private UserGameServiceImpl userGameService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private PlayerInfoServiceImpl PIS;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	private ResponseObj RO;
	
	@Autowired
	private ObtainDate obtainDate;
	
	ObjectMapper obj = new ObjectMapper();
	
	@GetMapping("/games/{gameId}")
	public Game getGame(@PathVariable int gameId) {
		Game game = new Game();
		try {
			game =  gameService.findById(gameId);
			if(game == null) {
				game.setStatus(404);
				game.setMessage("Game not found");
				return game;
			}
			game.setStatus(200);
			game.setMessage("Game fetched successfully");
			return game;
		}
		catch(Exception e) {
			game.setStatus(500);
			game.setMessage("Error occurred when fetching game");
			return game;
		}
	}
	
	@GetMapping("/games/{gameId}/data")
	public Game getGameData(@PathVariable int gameId) {
		Game game = gameService.findById(gameId);
		Game gameClone = game.cloneWithoutBoardStructures();
		return gameClone;
	}
	
	@GetMapping("/games/for/user/{userId}")
	public List<Game> getUserGames(@PathVariable int userId) {
		List<Integer> gameIds = new ArrayList<>();
		ArrayList<Game> games = new ArrayList<>();
		
		try {
			ArrayList<PlayerInfo> playerInfoObjs = PIS.findAllByPlayerInfoUserId(userId);
			
			if(playerInfoObjs.size() == 0) {
				Game game = new Game();
				
				game.setStatus(404);
				game.setMessage("No games found for the provided user id.");
				
				games.add(game);
				
				return games;
			}
			
			for(PlayerInfo PI : playerInfoObjs) {
				gameIds.add(PI.getPlayerInfoGameId());
			}
			
			games = (ArrayList<Game>) gameService.findByGameIdIn(gameIds);
			
			if(games.size() == 0) {
				Game game = new Game();
				
				game.setStatus(404);
				game.setMessage("No games found for the provided user id.");
				
				games.add(game);
				
				return games;
			}
			
			Game game = games.get(0);
			game.setStatus(200);
			game.setMessage("Game(s) found for the provided user id.");
			return games;
		}
		catch(Exception e) {
			Game game = new Game();
			
			game.setStatus(500);
			game.setMessage("An error occurred when attempting to fetch games.");
			
			return games;
		}
	}
	
	@Transactional
	@DeleteMapping("/games/{gameId}")
	public ResponseObj deleteGame(@PathVariable int gameId) {
		ResponseObj res = gameService.deleteByGameId(gameId);
		return res;
	}
	
	@PutMapping("/games/{gameId}")
	public Game saveGame(@RequestBody Game game) {
		Game savedGame = gameService.save(game);
		return savedGame;
	}
	
	// saves all game data except game board
	@PutMapping("/games/{gameId}/data")
	public ResponseObj saveGameData(@RequestBody GameDataObj updatedGameData, @PathVariable int gameId) {
		ResponseObj res = new ResponseObj();
		Game gameToUpdate = null;
		
		try {
			gameToUpdate = gameService.findById(gameId);
			
		}
		catch(Exception e) {
			res.setSucceeded(false);
			res.setMessage(e.getMessage());
			return res;
		}
		
		gameToUpdate.updateGameData(updatedGameData);
		
		try {
			gameService.save(gameToUpdate);
		}
		catch(Exception e) {
			res.setSucceeded(false);
			res.setMessage(e.getMessage());
			return res;
		}
		
		res.setSucceeded(true);
		res.setMessage("Game data for game " + gameId + " saved!");
		return res;
	}
	
	@PutMapping("/games/{gameId}/finish_turn")
	public Game finishTurn(@PathVariable int gameId) {
		Game gameToUpdate = gameService.findById(gameId);
		int currentPlayerIdx = gameToUpdate.getCurrentPlayerIdx();
		int playerSize = gameToUpdate.getPlayerSize() - 1;
		
		if(currentPlayerIdx == playerSize) {
			gameToUpdate.setCurrentPlayerIdx(0);
		}
		else {
			gameToUpdate.setCurrentPlayerIdx(currentPlayerIdx + 1);
		}
		gameToUpdate.setCurrDiceRoll(0);
		gameToUpdate.setCurrTurnPhaseIdx(0);
		gameService.save(gameToUpdate);
		return gameToUpdate;
	}
	
	@PutMapping("/games/dev-cards")
	public Game updateGame(@RequestBody Game updatedGame) {
				
		try {
			gameService.save(updatedGame);
			return updatedGame;
		} catch(Exception e) {
			return null;
		}	
	}
	
	@PutMapping("/games/{gameId}/initial_placements")
	public Game updateInitialGame(@PathVariable int gameId, @RequestBody Game incomingGameData) {
		try {
			Game gameToUpdate = gameService.findById(gameId);
			
			String initialGameInstructionsData = incomingGameData.getInitialGameInstructions();
			gameToUpdate.setInitialGameInstructions(initialGameInstructionsData);
			
			gameService.save(gameToUpdate);
			
			gameToUpdate.setStatus(200);
			gameToUpdate.setMessage("Initial game instructions updated successfully.");
			
			return gameToUpdate;
		}
		catch(Exception e) {
			Game responseGame = new Game();
			
			responseGame.setStatus(500);
			responseGame.setMessage("Error occurred when attempting to update initial game instructions.");
			
			return responseGame;
		}
	}
	
	@PutMapping("/games/{gameId}/start_game")
	public Game startGame(@PathVariable int gameId, @RequestBody Game game) {
		try {
			Game gameToUpdate = gameService.findById(gameId);
			
			gameToUpdate.setInitialGamePhase(1);
			gameToUpdate.setPlayers(game.getPlayers());
			gameToUpdate.setInitialGameInstructions(game.getInitialGameInstructions());
			
			String date = obtainDate.getTodayDate(obtainDate.getDateFormat(), obtainDate.getTimeZone());
			String time = obtainDate.getCurrentTime(obtainDate.getTimeFormat(), obtainDate.getTimeZone());
			String timestamp = date + " - " + time;
			gameToUpdate.setLastUpdated(timestamp);
			
			gameService.save(gameToUpdate);
			
			gameToUpdate.setStatus(200);
			gameToUpdate.setMessage("Game started successfully.");
			
			return gameToUpdate;
		}
		catch(Exception e) {
			Game responseGame = new Game();
						
			responseGame.setStatus(500);
			responseGame.setMessage("Error occurred when attempting to start game.");
			
			return responseGame;
		}
	}
	
	@PutMapping("/games/{gameId}/start_main_game")
	public Game initiateMainGamePhase(@PathVariable int gameId) {
		
		try {
			Game gameToUpdate = gameService.findById(gameId);
			
			String date = obtainDate.getTodayDate(obtainDate.getDateFormat(), obtainDate.getTimeZone());
			String time = obtainDate.getCurrentTime(obtainDate.getTimeFormat(), obtainDate.getTimeZone());
			String timestamp = date + " - " + time;
			gameToUpdate.setLastUpdated(timestamp);
			
			gameToUpdate.setInitialGamePhase(0);
			gameToUpdate.setMainGamePhase(1);
			
			gameService.save(gameToUpdate);
			
			gameToUpdate.setStatus(200);
			gameToUpdate.setMessage("Main game phase started successfully.");
			
			return gameToUpdate;
		}
		catch(Exception e) {
			Game responseGame = new Game();
			
			responseGame.setStatus(500);
			responseGame.setMessage("Error occurred when attempting to start main game phase.");
			
			return responseGame;
		}
	}
	
	@PutMapping("/games/{gameId}/dice_value_update/{value}")
	public Game updateDiceValue(@PathVariable int gameId, @PathVariable int value) {
		Game gameToUpdate = null;

		try {
			gameToUpdate = gameService.findById(gameId);

			String date = obtainDate.getTodayDate(obtainDate.getDateFormat(), obtainDate.getTimeZone());
			String time = obtainDate.getCurrentTime(obtainDate.getTimeFormat(), obtainDate.getTimeZone());
			String timestamp = date + " - " + time;

			gameToUpdate.setLastUpdated(timestamp);
			gameToUpdate.setCurrDiceRoll(value);

			Game savedGame = gameService.save(gameToUpdate);

			savedGame.setStatus(200);
			savedGame.setMessage("Updates game's current dice value");

			return savedGame;
		}
		catch(RuntimeException RE) {
			RE.printStackTrace();

			gameToUpdate = new Game();

			gameToUpdate.setStatus(500);
			gameToUpdate.setMessage("An error occurred when attempting to update game's current dice value");

			return gameToUpdate;
		}
	}
	
	@PutMapping("/games/{gameId}/turnPhase")
	public Game updateTurnPhase(@PathVariable int gameId) {
		Game gameToUpdate = null;
		int newTurnPhaseIdx = -1;
		try {
			gameToUpdate = gameService.findById(gameId);
			int currTurnPhaseIdx = gameToUpdate.getCurrTurnPhaseIdx();
			if(currTurnPhaseIdx == 2) {
				newTurnPhaseIdx = 0;
			}
			else {
				newTurnPhaseIdx = currTurnPhaseIdx += 1;
			}
			gameToUpdate.setCurrTurnPhaseIdx(newTurnPhaseIdx);
			gameService.save(gameToUpdate);
			gameToUpdate.setStatus(200);
			gameToUpdate.setMessage("successfully updated turn phase.");
			return gameToUpdate;
		}
		catch(RuntimeException RE) {
			RE.printStackTrace();
			gameToUpdate = new Game();
			gameToUpdate.setStatus(500);
			gameToUpdate.setMessage("Error when attempting to update the turn phase. Please try again.");
			return gameToUpdate;
		}
	}
}
