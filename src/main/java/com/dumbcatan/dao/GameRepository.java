package com.dumbcatan.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dumbcatan.entity.Game;

public interface GameRepository extends JpaRepository<Game, Integer> {
	
	Optional<Game> findById(int id);
		
	List<Game> findByOwnerId(int id);
	
	List<Game> findByGameIdIn(List<Integer> ids);
	
	void deleteByGameId(int id);
}