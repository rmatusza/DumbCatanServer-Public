package com.dumbcatan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dumbcatan.dao.UserGameRepository;
import com.dumbcatan.entity.UserGame;

@Service
public class UserGameServiceImpl implements UserGameService {
	
	@Autowired
	UserGameRepository userGameRepo;

	@Override
	public List<UserGame> findByUserGamesUserId(int id) {
		
		List<UserGame> userGames = userGameRepo.findByUserGamesUserId(id);
		
		if(userGames.size() == 0) {
			throw new RuntimeException("No UserGame records found");
		}
		
		return userGames;
	}

	@Override
	// TODO - UPDATE TO INCLUDE TRY CATCH AND RETURN RESPONSE OBJ 
	public void save(UserGame userGame) {
		userGameRepo.save(userGame);
	}
	
	public void saveUserGameTransactional(UserGame userGame) {
		userGameRepo.save(userGame);
	}

	@Override
	public UserGame findByUserGamesUserIdAndUserGamesGameId(int userGamesUserId, int userGamesGameId) {
		
		Optional<UserGame> userGame = userGameRepo.findByUserGamesUserIdAndUserGamesGameId(userGamesUserId, userGamesGameId);
		
		if (userGame.isPresent()) {
			return userGame.get();
		} else {
			return null;
		}
	}
}
