package com.dumbcatan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dumbcatan.entity.UserGame;


public interface UserGameService {
	public List<UserGame> findByUserGamesUserId(int id);
	
	public UserGame findByUserGamesUserIdAndUserGamesGameId(int userId, int gameId);
	
	public void save(UserGame userGame);
	
	public void saveUserGameTransactional(UserGame userGame);
}
