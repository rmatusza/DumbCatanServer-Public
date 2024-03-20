package com.dumbcatan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dumbcatan.entity.http.ResponseEntityHelper;

@Entity
@Table(name="user_games")
public class UserGame extends ResponseEntityHelper{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="user_games_user_id")
	private int userGamesUserId;
	
	@Column(name="user_games_game_id")
	private int userGamesGameId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserGamesUserId() {
		return userGamesUserId;
	}

	public void setUserGamesUserId(int userGamesUserId) {
		this.userGamesUserId = userGamesUserId;
	}

	public int getUserGamesGameId() {
		return userGamesGameId;
	}

	public void setUserGamesGameId(int userGamesGameId) {
		this.userGamesGameId = userGamesGameId;
	}
	
	public UserGame() {
		
	}

	public UserGame(int userGamesUserId, int userGamesGameId) {
		this.userGamesUserId = userGamesUserId;
		this.userGamesGameId = userGamesGameId;
	}
}
