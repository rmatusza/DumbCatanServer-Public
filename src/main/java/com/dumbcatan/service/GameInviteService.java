package com.dumbcatan.service;

import java.util.List;
import java.util.Optional;

import com.dumbcatan.entity.GameInvite;

public interface GameInviteService {
	
	public void save(GameInvite invite);
	
	public GameInvite findById(int id);
	
	public List<GameInvite> findByRecipientId(int id);
	
	public GameInvite findByRecipientIdAndGameId(int recipientId, int gameId);
	
	public void deleteById(int id);
	
	public void deleteByRecipientIdAndGameId(int recipientId, int gameId);
}
