package com.dumbcatan.entity;

import javax.persistence.*;

import com.dumbcatan.entity.http.ResponseEntityHelper;

@Entity
@Table(name="game_boards")
public class GameBoard extends ResponseEntityHelper{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="board_id")
	private int boardId;

	@Column(name="game_board_game_id", insertable = false, updatable = false)
	private int gameBoardGameId;
	
	@Column(name="game_board")
	private String gameBoard;

	@OneToOne
	@JoinColumn(name = "game_board_game_id", referencedColumnName = "game_id")
	private Game game;

	public void setGame(Game game){
		this.game = game;
	}

	public int getGameBoardGameId() {
		return gameBoardGameId;
	}

	public void setGameBoardGameId(int gameBoardGameId) {
		this.gameBoardGameId = gameBoardGameId;
	}

	public String getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(String gameBoard) {
		this.gameBoard = gameBoard;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public GameBoard() {
		
	}

//	public GameBoard(int boardId, int gameBoardGameId, String gameBoard) {
//		this.boardId = boardId;
//		this.gameBoardGameId = gameBoardGameId;
//		this.gameBoard = gameBoard;
//	}

	public GameBoard(int boardId, String gameBoard) {
		this.boardId = boardId;
		this.gameBoard = gameBoard;
	}
}
