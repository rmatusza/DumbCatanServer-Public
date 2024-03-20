package com.dumbcatan.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dumbcatan.entity.GameBoard;

@Repository
public interface GameBoardRepository extends JpaRepository<GameBoard, Integer> {
		
	public void deleteGameBoardByBoardId(int boardId);
	
	public Optional<GameBoard> findByGameBoardGameId(int gameId);
}
