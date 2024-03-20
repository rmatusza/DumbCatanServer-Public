package com.dumbcatan.entity;

// Class for logging data
public class Resources {
	
	private String gainedResources;
	private String playerDataBefore;
	private String playerDataAfter;
	
	public String getGainedResources() {
		return gainedResources;
	}
	public void setGainedResources(String gainedResources) {
		this.gainedResources = gainedResources;
	}
	public String getPlayerDataBefore() {
		return playerDataBefore;
	}
	public void setPlayerDataBefore(String playerDataBefore) {
		this.playerDataBefore = playerDataBefore;
	}
	public String getPlayerDataAfter() {
		return playerDataAfter;
	}
	public void setPlayerDataAfter(String playerDataAfter) {
		this.playerDataAfter = playerDataAfter;
	}
	public Resources(String gainedResources, String playerDataBefore, String playerDataAfter) {
		super();
		this.gainedResources = gainedResources;
		this.playerDataBefore = playerDataBefore;
		this.playerDataAfter = playerDataAfter;
	}
	
}
