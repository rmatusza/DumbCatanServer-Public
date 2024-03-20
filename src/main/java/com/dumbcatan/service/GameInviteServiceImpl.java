package com.dumbcatan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.dumbcatan.dao.GameInviteRepository;
import com.dumbcatan.entity.GameInvite;

@Transactional
@Service
public class GameInviteServiceImpl implements GameInviteService {
	
	@Autowired
	GameInviteRepository gameInviteRepo;

	@Override
	public void save(GameInvite invite) {
		gameInviteRepo.save(invite);
	}

	@Override
	public GameInvite findById(int id) {
		Optional<GameInvite> result = gameInviteRepo.findById(id);

		GameInvite invite = null;

		if (result.isPresent()) {
			invite = result.get();
		} else {
			throw new RuntimeException("Did not find invite with id: " + id);
		}

		return invite;
	}

	@Override
	public List<GameInvite> findByRecipientId(int id) {
		
		List<GameInvite> result = gameInviteRepo.findByRecipientId(id);

		
		if (result.size() != 0) {
			return result;
		} else {
//			throw new ResponseStatusException(
//					  HttpStatus.NOT_FOUND, "No invites found with associated user id: " + id
//					);
			return result;
		}
	}

	@Override
	public GameInvite findByRecipientIdAndGameId(int recipientId, int gameId) {
		
		Optional<GameInvite> result = gameInviteRepo.findByRecipientIdAndGameId(recipientId, gameId);
		
		GameInvite invite = null;
		
		if(result.isPresent()) {
			invite = result.get();
			return invite;
		} else {
//			throw new ResponseStatusException(
//					  HttpStatus.NOT_FOUND, "Game invite with id: " + gameId + " was not found for recipient with id: " + recipientId
//					);
			return null;
		}
	}

	@Override
	public void deleteById(int id) {
		gameInviteRepo.deleteById(id);
	}

	@Override
	public void deleteByRecipientIdAndGameId(int recipientId, int gameId) {
		
		try {
			gameInviteRepo.deleteByRecipientIdAndGameId(recipientId, gameId);		
		} catch(Exception e) {
			throw new RuntimeException("ERROR: Resource was not deleted from the database");
		}
	}
}
