package com.dumbcatan.service;

import java.util.List;


import com.dumbcatan.entity.Game;
import com.dumbcatan.entity.http.ResponseObj;

public interface GameService {
	
	public List<Game> findAll();
	
	public Game findById(int id);
	
	public List<Game> findByGameIdIn(List<Integer> id);
	
	public Game save(Game game);
	
//	public void save(Game game);
	
	public Game saveTransactional(Game game);
	
	public ResponseObj deleteByGameId(int id);
	
	public List<Game> findByOwnerId(int id);
	
}
