package com.dumbcatan.entity.http;

import java.util.ArrayList;
import java.util.HashMap;

// created this class to test jackson to see if i could use an object maper object
// to extract and create a class instance out of the player info field of the player info table
// -> no longer need but kept around if i wanted to experiment with it later
public class PlayerInfoObj {

	private int initialGameSetupIteration;
	private boolean overSevenCardPenalty;
	private String username;
	private int id;
	private String color;
	private int points;
	private HashMap<String, HashMap<String, Integer>> hand;
	private HashMap<String, HashMap<String, Integer>> devCards;
	private HashMap<String, HashMap<String, Integer>> lockedDevCards;
	private HashMap<String, ArrayList<Integer>> structures;
	
	public int getInitialGameSetupIteration() {
		return initialGameSetupIteration;
	}
	
	public void setInitialGameSetupIteration(int initialGameSetupIteration) {
		this.initialGameSetupIteration = initialGameSetupIteration;
	}
	
	public boolean isOverSevenCardPenalty() {
		return overSevenCardPenalty;
	}
	
	public void setOverSevenCardPenalty(boolean overSevenCardPenalty) {
		this.overSevenCardPenalty = overSevenCardPenalty;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public HashMap<String, HashMap<String, Integer>> getHand() {
		return hand;
	}
	
	public void setHand(HashMap<String, HashMap<String, Integer>> hand) {
		this.hand = hand;
	}
	
	public HashMap<String, HashMap<String, Integer>> getDevCards() {
		return devCards;
	}
	
	public void setDevCards(HashMap<String, HashMap<String, Integer>> devCards) {
		this.devCards = devCards;
	}
	
	public HashMap<String, HashMap<String, Integer>> getLockedDevCards() {
		return lockedDevCards;
	}
	
	public void setLockedDevCards(HashMap<String, HashMap<String, Integer>> lockedDevCards) {
		this.lockedDevCards = lockedDevCards;
	}
	
	public HashMap<String, ArrayList<Integer>> getStructures() {
		return structures;
	}
	
	public void setStructures(HashMap<String, ArrayList<Integer>> structures) {
		this.structures = structures;
	}
	
	public PlayerInfoObj() {
	}
	
	public PlayerInfoObj(int initialGameSetupIteration, boolean overSevenCardPenalty, String username, int id,
			String color, int points, HashMap<String, HashMap<String, Integer>> hand,
			HashMap<String, HashMap<String, Integer>> devCards,
			HashMap<String, HashMap<String, Integer>> lockedDevCards, HashMap<String, ArrayList<Integer>> structures) {
		this.initialGameSetupIteration = initialGameSetupIteration;
		this.overSevenCardPenalty = overSevenCardPenalty;
		this.username = username;
		this.id = id;
		this.color = color;
		this.points = points;
		this.hand = hand;
		this.devCards = devCards;
		this.lockedDevCards = lockedDevCards;
		this.structures = structures;
	}
}
