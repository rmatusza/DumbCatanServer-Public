package com.dumbcatan.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dumbcatan.entity.GameInvite;

public interface GameInviteRepository extends JpaRepository<GameInvite, Integer> {
	
	Optional<GameInvite> findById(int id);
	
	List<GameInvite> findByRecipientId(int id);
	
	Optional<GameInvite> findByRecipientIdAndGameId(int recipientId, int gameId);
	
	List<GameInvite> deleteByRecipientIdAndGameId(int recipientId, int gameId);
	
}
