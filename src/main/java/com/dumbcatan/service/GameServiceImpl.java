package com.dumbcatan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dumbcatan.dao.GameRepository;
import com.dumbcatan.entity.Game;
import com.dumbcatan.entity.http.ResponseObj;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	private GameRepository gameRepo;
	
	@Autowired
	private ResponseObj RO;

	@Override
	public List<Game> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game findById(int id) {

		Optional<Game> result = gameRepo.findById(id);

		Game game = null;

		if (result.isPresent()) {
			game = result.get();
		} else {
			return null;
		}

		return game;
	}

	@Override
	public Game save(Game game) {
		try {
			gameRepo.save(game);
			game.setStatus(200);
			game.setMessage("Game saved");
			return game;
		}
		catch(Exception e) {
			game.setStatus(500);
			game.setMessage("There was an error when trying to save game: " + e.getMessage());
			return game;
		}
	}
	
//	@Override
//	public void save(Game game) {
//		gameRepo.save(game);
//	}

	@Override 
	public Game saveTransactional(Game game) {
		Game newGame = gameRepo.save(game);
		return newGame;
	}

	@Override
	public ResponseObj deleteByGameId(int id) {
		ResponseObj res = new ResponseObj();
		res.setStatus(500);
		
		try {
			gameRepo.deleteByGameId(id);
			res.setStatus(200);
			res.setSucceeded(true);
			res.setMessage("Game " + id + " deleted");
			return res;
		}
		catch(Exception e) {
//			System.out.println(e.getMessage());
			res.setStatus(500);
			res.setSucceeded(false);
			res.setMessage("Error occurred when attempting to delete game " + id +". Please try again.");
			return res;
		}
		
	}

	@Override
	public List<Game> findByOwnerId(int id) {
		List<Game> result = gameRepo.findByOwnerId(id);

		
		if (result.size() != 0) {
			return result;
		} else {
			throw new RuntimeException("No games found with associated user id - " + id);
		}
	}

	@Override
	public List<Game> findByGameIdIn(List<Integer> ids) {
		
		List<Game> games = gameRepo.findByGameIdIn(ids);
		
		if(games.size() == 0) {
			throw new RuntimeException("No Game Invites Found");
		}
		
		return games;
	}
}
