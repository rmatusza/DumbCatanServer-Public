package com.dumbcatan.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dumbcatan.dao.PlayerInfoRepository;
import com.dumbcatan.entity.PlayerInfo;
import com.dumbcatan.entity.http.ResponseObj;

@Service
public class PlayerInfoServiceImpl implements PlayerInfoService {

	@Autowired
	private PlayerInfoRepository playerInfoRepo;
	
	@Autowired
	private ResponseObj RO;

	
	@Override
	public PlayerInfo save(PlayerInfo playerInfo) {
		try {
			playerInfoRepo.save(playerInfo);
			playerInfo.setStatus(200);
			playerInfo.setMessage("PlayerInfo record created");
			return playerInfo;
			
		} catch(Exception e) {
			playerInfo.setStatus(500);
			playerInfo.setMessage("An error occurred when attempting to create the PlayerInfo record: " + e.getMessage());
			return playerInfo;
		}
	}
	
	@Override
	public PlayerInfo savePlayerInfoTransactional(PlayerInfo playerInfo) {
		playerInfoRepo.save(playerInfo);
		return playerInfo;
	}

	@Override
	public ArrayList<PlayerInfo> saveAll(ArrayList<PlayerInfo> playerInfo) {
		return (ArrayList<PlayerInfo>) playerInfoRepo.saveAll(playerInfo);
	}

	@Override
	public PlayerInfo findByPlayerInfoUserIdAndPlayerInfoGameId(int playerInfoUserId, int playerInfoGameId) {
		
		Optional<PlayerInfo> playerInfo = playerInfoRepo.findByPlayerInfoUserIdAndPlayerInfoGameId(playerInfoUserId, playerInfoGameId);

		if (playerInfo.isPresent()) {
			return playerInfo.get();
		} else {
			return null;
		}
	}

	@Override
	public ArrayList<PlayerInfo> findAllByPlayerInfoUserIdAndPlayerInfoGameId(List<Integer> playerInfoUserIds, int playerInfoGameId) {
		return (ArrayList<PlayerInfo>) playerInfoRepo.findAllByPlayerInfoUserIdInAndPlayerInfoGameId(playerInfoUserIds, playerInfoGameId);
	}

	@Override
	public ArrayList<PlayerInfo> findAllByPlayerInfoUserId(int playerInfoUserId) {
		ArrayList<PlayerInfo> playerInfoObjs = playerInfoRepo.findAllByPlayerInfoUserId(playerInfoUserId);
		return playerInfoObjs;
	}
	
	


}
