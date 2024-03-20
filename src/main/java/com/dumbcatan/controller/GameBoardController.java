package com.dumbcatan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dumbcatan.entity.GameBoard;
import com.dumbcatan.entity.http.ResponseObj;
import com.dumbcatan.service.GameBoardServiceImpl;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/dumbCatan")
public class GameBoardController {

	@Autowired
	GameBoardServiceImpl GBS;
	
	@PutMapping("/games/{gameId}/board")
	public GameBoard saveBoard(@RequestBody GameBoard incomingGameBoard, @PathVariable int gameId) {
		GameBoard updatedGameBoard = GBS.findByGameBoardGameId(gameId);
		updatedGameBoard.setGameBoard(incomingGameBoard.getGameBoard());
		GameBoard savedGameBoard = GBS.saveGameBoard(updatedGameBoard);
		return savedGameBoard;
	}
	
	@GetMapping("/games/{gameId}/board")
	public GameBoard getBoard(@PathVariable int gameId) {
		return GBS.findByGameBoardGameId(gameId);
	}
	
	@DeleteMapping("/boards/{boardId}")
	public ResponseObj deleteGameBoard(@PathVariable int gameBoardId) {
		ResponseObj RO = GBS.deleteGameBoardByBoardId(gameBoardId);
		return RO;
	}
}
