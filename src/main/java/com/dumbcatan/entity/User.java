package com.dumbcatan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dumbcatan.entity.http.ResponseEntityHelper;

@Entity
@Table(name="users")
public class User extends ResponseEntityHelper {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;
	
	@Column(name="username")
	private String username;

	@Column(name="password")
	private String password;
	
	@Column(name="role")
	private String role;
	
	@Column(name="avatar_url")
	private String avatarURL;
	
	@Column(name="wins")
	private int wins;
	
	@Column(name="losses")
	private int losses;
	
	@Column(name="logged_in")
	private int loggedIn;
	
	@Column(name="active_games")
	private int activeGames;
	
	public int getActiveGames() {
		return activeGames;
	}

	public void setActiveGames(int activeGames) {
		this.activeGames = activeGames;
	}

	public int getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(int loggedIn) {
		this.loggedIn = loggedIn;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public String getAvatarURL() {
		return avatarURL;
	}

	public void setAvatarURL(String avatarURL) {
		this.avatarURL = avatarURL;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public User() {
		
	}

	public User(String username, String password, String role, String avatarURL, int wins, int losses, int loggedIn) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.avatarURL = avatarURL;
		this.wins = wins;
		this.losses = losses;
		this.loggedIn = loggedIn;
	}

}
