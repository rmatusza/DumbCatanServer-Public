package com.dumbcatan.service;

import com.dumbcatan.entity.GameBoard;
import com.dumbcatan.entity.http.ResponseObj;

public interface GameBoardService {

	public GameBoard saveGameBoard(GameBoard GB);

	public GameBoard save(GameBoard GB);
	
	public void saveGameBoardTransactional(GameBoard GB);
	
	public ResponseObj deleteGameBoardByBoardId(int BoardId);
	
	public GameBoard findByGameBoardGameId(int boardId);
	
}
