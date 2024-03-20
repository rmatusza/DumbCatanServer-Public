package com.dumbcatan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dumbcatan.entity.PlayerInfo;
import com.dumbcatan.entity.http.ResponseObj;
import com.dumbcatan.service.PlayerInfoService;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api")
public class PlayerInfoController {
	
	@Autowired
	private PlayerInfoService playerInfoService;
	
	@Autowired
	ResponseObj RO;
	
	private static String base64Encode(String value) {
        try {
            return Base64.getEncoder()
                        .encodeToString(value.getBytes(StandardCharsets.UTF_8.toString()));        
        } catch(UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }
	
	@PostMapping("/player_info")
	public PlayerInfo createPlayerInfo(@RequestBody PlayerInfo playerInfo) {
		PlayerInfo savedPlayerInfo = playerInfoService.save(playerInfo);
		return savedPlayerInfo;
	}
	
	@PutMapping("/player_info")
	public PlayerInfo savePlayerInfo(@RequestBody PlayerInfo playerInfo, @RequestParam int gameId) {
		playerInfoService.save(playerInfo);
		return playerInfo;
	}
	
	@PutMapping("/player_info/{playerId}/bulk_update")
	public PlayerInfo bulkSavePlayerInfo(@RequestBody ArrayList<PlayerInfo> playerInfo, @PathVariable int playerId) {
		PlayerInfo updatedPlayer = new PlayerInfo();
		
		try {
			ArrayList<PlayerInfo> updatedPlayers = playerInfoService.saveAll(playerInfo);
			for(int i=0; i<updatedPlayers.size(); i++) {
				PlayerInfo currPlayer = updatedPlayers.get(i);
				if(currPlayer.getPlayerInfoUserId() == playerId) {
					currPlayer.setStatus(200);
					currPlayer.setMessage("PlayerInfo saved.");
					return currPlayer;
				}
			}
			updatedPlayer.setStatus(500);
			updatedPlayer.setMessage("Player id does not match with any of the provided PlayerInfo objects.");
			return updatedPlayer;
		}
		catch(Exception e) {
			updatedPlayer.setStatus(500);
			updatedPlayer.setMessage("Error encountered when trying to save PlayerInfo.");
			return updatedPlayer;
		}
	}
	
	// REFACTOR NOTE: in the future use a JPL query 
	@GetMapping("/player_info")
	public List<PlayerInfo> getPlayerInfoByUserIdAndGameId(@RequestParam int gameId, @RequestParam List<Integer> userIds) {
		ArrayList<PlayerInfo> playerInfos = new ArrayList<>();
		
		try {
			playerInfos = playerInfoService.findAllByPlayerInfoUserIdAndPlayerInfoGameId(userIds, gameId);
			if(playerInfos.size() == 0) {
				PlayerInfo pI = new PlayerInfo();
				pI.setStatus(404);
				pI.setMessage("No users were found with the provided user ids");
				playerInfos.add(pI);
				return playerInfos;
			}
			PlayerInfo pI = playerInfos.get(0);
			pI.setStatus(200);
			pI.setMessage("Player info objects retrieved successfully");
			return playerInfos;
		}
		catch(Exception e) {
			PlayerInfo pI = new PlayerInfo();
			pI.setStatus(500);
			pI.setMessage("Error retrieving player info objects");
			playerInfos.add(pI);
			return playerInfos;
		}				
	}
	
	
	@PutMapping("/player_info/over-seven-card-penalty/add")
	public ResponseObj addOverSevenCardPenaltyUpdate(@RequestParam int gameId, @RequestParam List<Integer> userIds) {
		ArrayList<PlayerInfo> playerInfos = null;
		try {
			playerInfos = playerInfoService.findAllByPlayerInfoUserIdAndPlayerInfoGameId(userIds, gameId);
		} catch(DataAccessException e) {
			e.printStackTrace();
			ResponseObj res = new ResponseObj(false, e.getMessage());
			return res;
		}
		for(PlayerInfo pI: playerInfos) {
			pI.setOverSevenCardPenalty(1);
		}
		try {
			playerInfoService.saveAll(playerInfos);
			ResponseObj res = new ResponseObj(true, "added '1' to OSCP column");
			return res;
		} catch(DataAccessException e) {
			e.printStackTrace();
			ResponseObj res = new ResponseObj(false, e.getMessage());
			return res;
		}
	}
	
	@PutMapping("/player_info/over-seven-card-penalty/remove")
	public ResponseObj removeOverSevenCardPenaltyUpdate(@RequestParam int gameId, @RequestParam List<Integer> userIds) {
		ArrayList<PlayerInfo> playerInfos = null;
		try {
			playerInfos = playerInfoService.findAllByPlayerInfoUserIdAndPlayerInfoGameId(userIds, gameId);
		} catch(DataAccessException e) {
			e.printStackTrace();
			ResponseObj res = new ResponseObj(false, e.getMessage());
			return res;
		}
		
		
		for(PlayerInfo pI: playerInfos) {
			pI.setOverSevenCardPenalty(0);
		}
		
		try {
			playerInfoService.saveAll(playerInfos);
			ResponseObj res = new ResponseObj(true, "added '0' to OSCP column");
			return res;
		} catch(DataAccessException e) {
			e.printStackTrace();
			ResponseObj res = new ResponseObj(false, e.getMessage());
			return res;
		}
	}
	
	@GetMapping("/player_info/over-seven-card-penalty/status")
	public ResponseObj OSCPStatus(@RequestParam int gameId, @RequestParam List<Integer> userIds) {
		ResponseObj res = new ResponseObj();
		ArrayList<PlayerInfo> playerInfos = null;
		boolean isPendingOSCP = false;
		try {
			playerInfos = playerInfoService.findAllByPlayerInfoUserIdAndPlayerInfoGameId(userIds, gameId);
			for(PlayerInfo player : playerInfos) {
				if(player.getOverSevenCardPenalty() == 1) {
					isPendingOSCP = true;
				}
			}
			res.setIsOSCP(isPendingOSCP);
			return res;
		} catch(DataAccessException e) {
			e.printStackTrace();
			res.setSucceeded(false);
			res.setMessage(e.getMessage());
			return res;
		}
	}
	
	// REFACTOR NOTE: in the future add all of the stealing functionality here in the backend
	// will need to figure out how to parse deeply nested json into an object to do this
}