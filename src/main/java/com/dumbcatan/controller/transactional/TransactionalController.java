package com.dumbcatan.controller.transactional;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dumbcatan.entity.Game;
import com.dumbcatan.entity.GameBoard;
import com.dumbcatan.entity.PlayerInfo;
import com.dumbcatan.entity.Trade;
import com.dumbcatan.entity.User;
import com.dumbcatan.entity.UserGame;
import com.dumbcatan.entity.http.ResponseObj;
import com.dumbcatan.service.GameBoardServiceImpl;
import com.dumbcatan.service.GameServiceImpl;
import com.dumbcatan.service.PlayerInfoServiceImpl;
import com.dumbcatan.service.TradeServiceImpl;
import com.dumbcatan.service.UserGameServiceImpl;
import com.dumbcatan.service.UserServiceImpl;
import com.dumbcatan.entity.transactional.GameAndBoard;
import com.dumbcatan.entity.transactional.NewGameDataObj;
import com.dumbcatan.entity.transactional.PlayerInfoGameBoard;
import com.dumbcatan.entity.transactional.PlayerInfoGameObj;
import com.dumbcatan.entity.transactional.TradeAndPlayerInfo;
import com.dumbcatan.util.ObtainDate;

import javax.transaction.Transactional;


@CrossOrigin(origins="*")
@RestController
@RequestMapping("/dumbCatan")
public class TransactionalController {
	
	@Autowired
	private ResponseObj RO;
	
	@Autowired
	private PlayerInfoServiceImpl PIS;
	
	@Autowired
	private GameServiceImpl GS;
	
	@Autowired
	private GameBoardServiceImpl GBS;
	
	@Autowired
	private UserGameServiceImpl UGS;
	
	@Autowired
	private TradeServiceImpl TSI;
	
	@Autowired
	private UserServiceImpl US;
		
	@Autowired
	ObtainDate obtainDate;
	
	private ByteBuffer bbuf = ByteBuffer.allocate(1024);
	
	private Charset charset = Charset.forName("UTF-8");
	
	@Transactional
	@PutMapping("/transactionals/user/{userId}/game/{gameId}/rolledSevenUpdate")
	public PlayerInfoGameObj rolledSevenUpdate(@RequestParam List<Integer> userIds, @PathVariable int gameId, @PathVariable int userId, @RequestBody PlayerInfo playerInfo) {
		PlayerInfoGameObj PIGO = new PlayerInfoGameObj();
		try {
			// fetch other player info objects using ids
			// update over seven card penalty field for all other users
			// save all user data
			ArrayList<PlayerInfo> playerInfos = PIS.findAllByPlayerInfoUserIdAndPlayerInfoGameId(userIds, gameId);
			// set OSCP to 1 whether or not it's needed b/c one or more of the players may not be logged in - this will prompt client application to check
			// also important b/c ordinarily 
			for(PlayerInfo pI : playerInfos) {
				pI.setOverSevenCardPenalty(1);
			}
			playerInfos.add(playerInfo);
			PIS.saveAll(playerInfos);
			
			// update the game with the rolled 7 and the time stamp
			String date = obtainDate.getTodayDate(obtainDate.getDateFormat(), obtainDate.getTimeZone());
			String time = obtainDate.getCurrentTime(obtainDate.getTimeFormat(), obtainDate.getTimeZone());
			String timestamp = date + " - " + time;
			
			Game game = GS.findById(gameId);
			game.setLastUpdated(timestamp);
			game.setCurrDiceRoll(7);
			GS.save(game);
			
			PIGO.setGame(game);
			PIGO.setPlayerInfo(playerInfo);
			PIGO.setStatus(200);
			PIGO.setMessage("Dice roll 7 transaction completed");
			return PIGO;
		}
		catch(Exception e) {
			PIGO.setStatus(500);
			PIGO.setMessage("An error occurred during the dice roll 7 transaction: " + e.getMessage());
			return PIGO;
		}
	}

	@Transactional
	@PutMapping("/transactionals/user/{userId}/game/{gameId}/rolledNonSevenUpdate")
	public PlayerInfoGameObj rolledNonSevenUpdate(@RequestBody ArrayList<PlayerInfo> playerInfos, @PathVariable int userId, @PathVariable int gameId, @RequestParam int diceValue, @RequestParam boolean currPlayerDataUpdated) {
		PlayerInfoGameObj PIGO = new PlayerInfoGameObj();
		Game gameToUpdate = null;
		
		
		try {
			
			if(playerInfos.size() > 0) {
				PIS.saveAll(playerInfos);
			}
			
			String date = obtainDate.getTodayDate(obtainDate.getDateFormat(), obtainDate.getTimeZone());
			String time = obtainDate.getCurrentTime(obtainDate.getTimeFormat(), obtainDate.getTimeZone());
			String timestamp = date + " - " + time;
			
			gameToUpdate = GS.findById(gameId);
			gameToUpdate.setLastUpdated(timestamp);
			gameToUpdate.setCurrDiceRoll(diceValue);
			
			GS.save(gameToUpdate);
			
			if(currPlayerDataUpdated) {
				for(PlayerInfo PI : playerInfos) {
					if(PI.getPlayerInfoUserId() == userId) {
						PIGO.setPlayerInfo(PI);
					}
				}
			}
			
			PIGO.setPlayerInfos(null);
			PIGO.setGame(gameToUpdate);
			PIGO.setStatus(200);
			PIGO.setMessage("Non-seven dice update successful");
			
			return PIGO;
		}
		catch(Exception e) {
			PIGO.setStatus(500);
			PIGO.setMessage("Non-seven dice update unsuccessful");
			return PIGO;
		}
	}
	
	@Transactional
	@GetMapping("/transactionals/game/{gameId}/for/user/{userId}")
	public PlayerInfoGameObj getGameAndPlayerInfo(@PathVariable int gameId, @PathVariable int userId) {
		PlayerInfoGameObj PIGO = new PlayerInfoGameObj();
		
		try {
			PlayerInfo PI = PIS.findByPlayerInfoUserIdAndPlayerInfoGameId(userId, gameId);
			Game game = GS.findById(gameId);
			if(PI == null) {
				PIGO.setStatus(404);
				PIGO.setMessage("PlayerInfo record not found.");
				return PIGO;
			}
			if(game == null) {
				PIGO.setStatus(404);
				PIGO.setMessage("Game record not found.");
				return PIGO;
			}
			PIGO.setStatus(200);
			PIGO.setMessage("Fetched game and player info records.");
			PIGO.setGame(game);
			PIGO.setPlayerInfo(PI);
			return PIGO;
		}
		catch(Exception e) {
			PIGO.setStatus(200);
			PIGO.setMessage("Fetched game and player info records.");
			return PIGO;
		}
	}
	
	@Transactional
	@PostMapping("/transactionals/new/game/created/by/user/{userId}")
	public NewGameDataObj createGame(@RequestBody NewGameDataObj NGDO,  @PathVariable int userId) {
		UserGame UG = new UserGame();
		
		try {
			
			Game game = NGDO.getGame();
//			System.out.println(game.getPlayerSize());
			String date = obtainDate.getTodayDate(obtainDate.getDateFormat(), obtainDate.getTimeZone());
			String time = obtainDate.getCurrentTime(obtainDate.getTimeFormat(), obtainDate.getTimeZone());
			String timestamp = date + " - " + time;

			game.setLastUpdated(timestamp);
			Game savedGame = GS.saveTransactional(game);
			
			GameBoard GB = NGDO.getGameBoard();
//			GB.setGameBoardGameId(savedGame.getGameId());
			GB.setGame(savedGame);
			GameBoard savedGB = GBS.saveGameBoard(GB);
			
			UG.setUserGamesUserId(userId);
			UG.setUserGamesGameId(savedGame.getGameId());
			UGS.saveUserGameTransactional(UG);
			
			PlayerInfo PI = NGDO.getPlayerInfo();
			PI.setPlayerInfoGameId(savedGame.getGameId());
			PlayerInfo savedPI = PIS.savePlayerInfoTransactional(PI);
			
			NGDO.setStatus(200);
			NGDO.setMessage("Game created: " + savedGame.getGameId());
			NGDO.setGame(savedGame);
			NGDO.setGameBoard(savedGB);
			NGDO.setPlayerInfo(savedPI);
			return NGDO;
		}
		catch(Exception e) {
			System.out.print(e.getMessage());
			
			NGDO.setStatus(500);
			NGDO.setMessage("An error occurred when try to create game: " + e.getMessage());
			NGDO.setGame(null);
			NGDO.setGameBoard(null);
			NGDO.setPlayerInfo(null);
			return NGDO;
		}

	}
	
	@Transactional
	@PutMapping("/transactionals/game/{gameId}/finish/turn")
	public PlayerInfoGameObj finishTurn(@PathVariable int gameId, @RequestBody PlayerInfo PI) {
		PlayerInfoGameObj PGO = new PlayerInfoGameObj();
		try {
			Game gameToUpdate = GS.findById(gameId);
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
			GS.save(gameToUpdate);
			PGO.setGame(gameToUpdate);
			
			PIS.save(PI);
			PGO.setPlayerInfo(PI);
			
			PGO.setStatus(200);
			PGO.setMessage("Finish turn transaction successful.");
			
			return PGO;
		}
		catch(Exception e) {
			PGO.setStatus(500);
			PGO.setMessage("There was an error when trying to perform finish turn transaction. Please try again.");
			return PGO;
		}
		
	}
	
	
	@Transactional
	@PostMapping("/transactionals/trade/{tradeId}/accepted")
	public PlayerInfo acceptTradeAndPerformCleanup(@PathVariable int tradeId, @RequestBody TradeAndPlayerInfo TPI) {
		PlayerInfo PI = TPI.getPlayerInfo();
		
		try {
			PIS.save(PI);
			TSI.deleteById(tradeId);
			
			PI.setStatus(200);
			PI.setMessage("Player data saved and trade deleted successfully");
			return PI;
		}
		catch(Exception e) {
			PI.setStatus(500);
			PI.setMessage("There was an error when attempting to save the player data and delete the trade. Please try again.");
			return PI;
		}
	}
	
	@Transactional
	@PutMapping("/transactionals/post/robber/placement/by/user/{userId}/data/update")
	public PlayerInfoGameBoard robberPlacementTransactional(@RequestBody PlayerInfoGameBoard PIGB, @PathVariable int userId) {
		ArrayList<PlayerInfo> playerInfos = PIGB.getPlayerInfos();
		GameBoard newGameBoardObj = PIGB.getGameBoard();
		try {
			GameBoard oldGameBoardUpdated = GBS.findByGameBoardGameId(newGameBoardObj.getGameBoardGameId());
			oldGameBoardUpdated.setGameBoard(newGameBoardObj.getGameBoard());

			PIS.saveAll(playerInfos);
			GBS.saveGameBoard(oldGameBoardUpdated);

			for(PlayerInfo PI : playerInfos) {
				if(PI.getPlayerInfoUserId() == userId) {
					PIGB.setPlayerInfo(PI);
					PIGB.setPlayerInfos(null);
				}
			}
			PIGB.setStatus(200);
			PIGB.setMessage("Player info and game board data saved successfully.");
			return PIGB;
		}
		catch(Exception e) {
			PIGB.setStatus(500);
			PIGB.setMessage("An error occurred when attempting to update the provided game board and player info data. Please try again.");
			PIGB.setPlayerInfos(null);
			PIGB.setGameBoard(null);
			return PIGB;
		}
	}
	
	@Transactional
	@PutMapping("/transactionals/save/game/and/board")
	public GameAndBoard saveGameAndBoard(@RequestBody GameAndBoard GAB, @RequestParam boolean userWonGame, @RequestParam int winnerId) {
		Game game = GAB.getGame();
		String GB = GAB.getBoard();
		GameBoard GBToUpdate = null;
		try {
			Game savedGame = GS.save(game);

			GBToUpdate = GBS.findByGameBoardGameId(game.getGameId());

			GBToUpdate.setGameBoard(GB);
			GameBoard savedBoard = GBS.save(GBToUpdate);

			if(userWonGame) {
				User winner = US.findById(winnerId);
				int winCount = winner.getWins();
				winner.setWins(winCount + 1);
				US.save(winner);
			}

			GAB.setBoard(savedBoard.getGameBoard());
			GAB.setGame(savedGame);
			GAB.setStatus(200);
			GAB.setMessage("Data saved successfully.");
			return GAB;
		}
		catch(Exception e) {
			GAB.setStatus(500);
			GAB.setMessage("An error occurred when attempting to save data. Please try again.");
			GAB.setGame(null);
			GAB.setBoard(null);
			return GAB;
		}
	}
	
	
	@Transactional
	@PutMapping("/transactionals/save/game/and/playerData")
	public PlayerInfoGameObj saveGameAndPlayerData(@RequestBody PlayerInfoGameObj PIG, @RequestParam boolean userWonGame, @RequestParam boolean newAward, @RequestParam int currUserId) {
		ArrayList<PlayerInfo> PI = PIG.getPlayerInfos();
		Game game = PIG.getGame();
		
		try {
			PIS.saveAll(PI);
			
			if(userWonGame || newAward) {
				GS.save(game);
				if(userWonGame) {
					User winner = US.findById(currUserId);
					int winCount = winner.getWins();
					winner.setWins(winCount + 1);
					US.save(winner);
				};
			};
			
			for(PlayerInfo player : PI) {
				if(player.getPlayerInfoUserId() == currUserId) {
					PIG.setPlayerInfo(player);
					PIG.setPlayerInfos(null);
				}
			}
			
			PIG.setStatus(200);
			PIG.setMessage("Data saved successfully.");
			return PIG;
		}
		catch(Exception e) {
			PIG.setStatus(500);
			PIG.setMessage("An error occurred when attempting to save data. Please try again.");
			PIG.setGame(null);
			PIG.setPlayerInfos(null);
			PIG.setPlayerInfo(null);
			return PIG;
		}
	}
	
	@Transactional
	@PutMapping("/transactionals/accept-trade/and/save-player-data")
	public PlayerInfo acceptTradeAndSavePlayerData(@RequestBody TradeAndPlayerInfo TPI) {
		Trade tradeToSave = TPI.getTrade();
		PlayerInfo playerInfoToSave = TPI.getPlayerInfo();
		
		tradeToSave.setTradeStatus(1);
		Trade savedTrade = TSI.save(tradeToSave);
		
		if(savedTrade.status != 200) {
			playerInfoToSave.setStatus(savedTrade.status);
			playerInfoToSave.setMessage(savedTrade.message);
			return playerInfoToSave;
		}
		
		PlayerInfo savedPlayerInfo = PIS.save(playerInfoToSave);
		
		return savedPlayerInfo;
	}
};
