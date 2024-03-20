package com.dumbcatan.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dumbcatan.entity.UserGame;

public interface UserGameRepository extends JpaRepository<UserGame, Integer> {
	
	Optional<UserGame> findByUserGamesUserIdAndUserGamesGameId(int userGamesUserId, int userGamesGameId);
	
	List<UserGame> findByUserGamesUserId(int userId);
}
