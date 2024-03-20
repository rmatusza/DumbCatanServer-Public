package com.dumbcatan.entity.transactional;

import com.dumbcatan.entity.GameBoard;
import org.springframework.stereotype.Component;

import com.dumbcatan.entity.Game;
import com.dumbcatan.entity.http.ResponseEntityHelper;

@Component
public class GameAndBoard extends ResponseEntityHelper {
	
	private Game game;
	private String board;

	public Game getGame() {
		return game;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public String getBoard() {
		return board;
	}
	
	public void setBoard(String board) {
		this.board = board;
	}

	public GameAndBoard(Game game, String board) {
		this.game = game;
		this.board = board;
	}
	
	public GameAndBoard() {
		
	}
	
}
