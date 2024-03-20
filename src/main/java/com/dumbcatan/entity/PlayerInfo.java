package com.dumbcatan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dumbcatan.entity.http.ResponseEntityHelper;

@Entity
@Table(name="player_infos")
public class PlayerInfo extends ResponseEntityHelper{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="player_info_game_id")
	private int playerInfoGameId;
	
	@Column(name="player_info_user_id")
	private int playerInfoUserId;
	
	@Column(name="player_info")
	private String playerInfo;
	
	@Column(name="over_seven_card_penalty")
	private int overSevenCardPenalty;
	
	@Column(name="placing_robber")
	private int placingRobber;
	
	@Column(name="turn_phase_idx")
	private int turnPhaseIdx;
	
	@Column(name="year_of_plenty_active")
	private int yearOfPlentyActive;
	
	@Column(name="monopoly_active")
	private int monopolyActive;
	
	@Column(name="road_building_active")
	private int roadBuildingActive;
	
	@Column(name="road_building_road_count")
	private int roadBuildingRoadCount;

	public int getRoadBuildingRoadCount() {
		return roadBuildingRoadCount;
	}

	public void setRoadBuildingRoadCount(int roadBuildingRoadCount) {
		this.roadBuildingRoadCount = roadBuildingRoadCount;
	}

	public int getYearOfPlentyActive() {
		return yearOfPlentyActive;
	}

	public void setYearOfPlentyActive(int yearOfPlentyActive) {
		this.yearOfPlentyActive = yearOfPlentyActive;
	}

	public int getMonopolyActive() {
		return monopolyActive;
	}

	public void setMonopolyActive(int monopolyActive) {
		this.monopolyActive = monopolyActive;
	}

	public int getRoadBuildingActive() {
		return roadBuildingActive;
	}

	public void setRoadBuildingActive(int roadBuildingActive) {
		this.roadBuildingActive = roadBuildingActive;
	}

	public int getTurnPhaseIdx() {
		return turnPhaseIdx;
	}

	public void setTurnPhaseIdx(int turnPhaseIdx) {
		this.turnPhaseIdx = turnPhaseIdx;
	}

	public int getPlacingRobber() {
		return placingRobber;
	}

	public void setPlacingRobber(int placingRobber) {
		this.placingRobber = placingRobber;
	}

	public int getOverSevenCardPenalty() {
		return overSevenCardPenalty;
	}

	public void setOverSevenCardPenalty(int overSevenCardPenalty) {
		this.overSevenCardPenalty = overSevenCardPenalty;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlayerInfoGameId() {
		return playerInfoGameId;
	}

	public void setPlayerInfoGameId(int playerInfoGameId) {
		this.playerInfoGameId = playerInfoGameId;
	}

	public int getPlayerInfoUserId() {
		return playerInfoUserId;
	}

	public void setPlayerInfoUserId(int playerInfoUserId) {
		this.playerInfoUserId = playerInfoUserId;
	}

	public String getPlayerInfo() {
		return playerInfo;
	}

	public void setPlayerInfo(String playerInfo) {
		this.playerInfo = playerInfo;
	}
	
	public PlayerInfo() {
		
	}

	public PlayerInfo(int playerInfoGameId, int playerInfoUserId, String playerInfo, int overSevenCardPenalty,
			int placingRobber, int turnPhaseIdx, int yearOfPlentyActive, int monopolyActive, int roadBuildingActive, int roadBuildingRoadCount) {
		this.playerInfoGameId = playerInfoGameId;
		this.playerInfoUserId = playerInfoUserId;
		this.playerInfo = playerInfo;
		this.overSevenCardPenalty = overSevenCardPenalty;
		this.placingRobber = placingRobber;
		this.turnPhaseIdx = turnPhaseIdx;
		this.yearOfPlentyActive = yearOfPlentyActive;
		this.monopolyActive = monopolyActive;
		this.roadBuildingActive = roadBuildingActive;
		this.roadBuildingRoadCount = roadBuildingRoadCount;
	}

}