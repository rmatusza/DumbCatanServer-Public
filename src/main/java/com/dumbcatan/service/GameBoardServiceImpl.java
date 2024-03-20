package com.dumbcatan.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dumbcatan.dao.GameBoardRepository;
import com.dumbcatan.entity.GameBoard;
import com.dumbcatan.entity.http.ResponseObj;

@Service
public class GameBoardServiceImpl implements GameBoardService {
	
	@Autowired
	private GameBoardRepository GBR;
	
	@Autowired
	private ResponseObj RO;

	@Override
	public GameBoard findByGameBoardGameId(int gameId) {
		GameBoard board = new GameBoard();

		try {
			Optional<GameBoard> GB = GBR.findByGameBoardGameId(gameId);

			if(!GB.isPresent()) {
				board.setStatus(404);
				board.setMessage("Game board associated with game " + gameId + " not found");
				return board;
			}
			board = GB.get();
			board.setStatus(200);
			return board;
		}
		catch(Exception e) {
			board.setStatus(500);
			board.setMessage("An error occurred when trying to retrieve game board associated with game " + gameId);
			return board;
		}
	}

	@Override
	public GameBoard saveGameBoard(GameBoard GB) {
		GameBoard savedGameBoard = null;
		
		try {
			savedGameBoard = GBR.save(GB);
			savedGameBoard.setStatus(200);
			savedGameBoard.setMessage("GameBoard saved");
			return savedGameBoard;
		}
		catch(Exception e) {
			savedGameBoard = new GameBoard();
			savedGameBoard.setStatus(500);
			savedGameBoard.setMessage("An error occurred when trying to save GameBoard");
			return savedGameBoard;
		}
	}

	@Override
	public GameBoard save(GameBoard GB) {
		return GBR.save(GB);
	}

	@Override
	public void saveGameBoardTransactional(GameBoard GB) {
		GBR.save(GB);
	}

	@Override
	public ResponseObj deleteGameBoardByBoardId(int boardId) {
		try {
			GBR.deleteGameBoardByBoardId(boardId);
			RO.setStatus(200);
			RO.setMessage("GameBoard deleted");
			return RO;
		}
		catch(Exception e) {
			RO.setStatus(500);
			RO.setMessage("An error occurred when trying to delete GameBoard");
			return RO;
		}
		
	}

}
