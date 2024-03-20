package com.dumbcatan.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dumbcatan.entity.PlayerInfo;

public interface PlayerInfoRepository extends JpaRepository<PlayerInfo, Integer> {
	
	Optional<PlayerInfo> findByPlayerInfoUserIdAndPlayerInfoGameId(int playerInfoUserId, int playerInfoGameId);

	ArrayList<PlayerInfo> findAllByPlayerInfoUserIdInAndPlayerInfoGameId(List<Integer> playerInfoUserIds, int playerInfoGameId);
	
	ArrayList<PlayerInfo> findAllByPlayerInfoUserId(int playerInfoUserId);		
}
