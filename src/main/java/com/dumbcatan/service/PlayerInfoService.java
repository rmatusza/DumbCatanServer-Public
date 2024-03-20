package com.dumbcatan.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dumbcatan.entity.PlayerInfo;

@Service
public interface PlayerInfoService {
	
//	public PlayerInfo findByPlayerInfoUserId(int id);
	
	public ArrayList<PlayerInfo> findAllByPlayerInfoUserIdAndPlayerInfoGameId(List<Integer> playerInfoUserIds, int playerInfoGameId);
	
	public ArrayList<PlayerInfo> findAllByPlayerInfoUserId(int playerInfoUserId);
		
	public PlayerInfo save(PlayerInfo playerInfo);
	
	public PlayerInfo savePlayerInfoTransactional(PlayerInfo playerInfo);
	
	public ArrayList<PlayerInfo> saveAll(ArrayList<PlayerInfo> playerInfo);

	PlayerInfo findByPlayerInfoUserIdAndPlayerInfoGameId(int playerInfoUserId, int playerInfoGameId);
	
}
