package com.dumbcatan.websocket.resource;

import java.util.ArrayList;
import java.util.HashMap;

public class WebsocketMessage {
	
	private String senderUsername;
	private int senderId;
	private String recipient;
	private int recipientId;
	private String message;
	private int diceValue;
	private String tiles;
	private boolean rolledSeven;
	private HashMap<String, String> stolenResources;
	private String senderData; 
	private int gameId;
	private int turnPhaseIdx;
	private String turnPhase;
	private int currPlayerIdx;
	private String tradeRecord;
	
	public String getTradeRecord() {
		return tradeRecord;
	}
	public void setTradeRecord(String tradeRecord) {
		this.tradeRecord = tradeRecord;
	}
	public int getCurrPlayerIdx() {
		return currPlayerIdx;
	}
	public void setCurrPlayerIdx(int currPlayerIdx) {
		this.currPlayerIdx = currPlayerIdx;
	}
	public String getTurnPhase() {
		return turnPhase;
	}
	public void setTurnPhase(String turnPhase) {
		this.turnPhase = turnPhase;
	}
	public int getTurnPhaseIdx() {
		return turnPhaseIdx;
	}
	public void setTurnPhaseIdx(int turnPhaseIdx) {
		this.turnPhaseIdx = turnPhaseIdx;
	}
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public String getSenderData() {
		return senderData;
	}
	public void setSenderData(String senderData) {
		this.senderData = senderData;
	}
	public HashMap<String, String> getStolenResources() {
		return stolenResources;
	}
	public void setStolenResources(HashMap<String, String> stolenResources) {
		this.stolenResources = stolenResources;
	}
	public boolean isRolledSeven() {
		return rolledSeven;
	}
	public void setRolledSeven(boolean rolledSeven) {
		this.rolledSeven = rolledSeven;
	}
	public String getTiles() {
		return tiles;
	}
	public void setTiles(String tiles) {
		this.tiles = tiles;
	}
	public int getDiceValue() {
		return diceValue;
	}
	public void setDiceValue(int diceValue) {
		this.diceValue = diceValue;
	}
	public String getSenderUsername() {
		return senderUsername;
	}
	public void setSenderUsername(String senderUsername) {
		this.senderUsername = senderUsername;
	}
	public int getSenderId() {
		return senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public int getRecipientId() {
		return recipientId;
	}
	public void setRecipientId(int recipientId) {
		this.recipientId = recipientId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	public WebsocketMessage(String senderUsername, int senderId, String recipient, int recipientId, String message,
			int diceValue, String tiles, boolean rolledSeven, HashMap<String, String> stolenResources, String senderData,
			int gameId, int turnPhaseIdx, String turnPhase, int currPlayerIdx, String tradeRecord) {
		this.senderUsername = senderUsername;
		this.senderId = senderId;
		this.recipient = recipient;
		this.recipientId = recipientId;
		this.message = message;
		this.diceValue = diceValue;
		this.tiles = tiles;
		this.rolledSeven = rolledSeven;
		this.stolenResources = stolenResources;
		this.senderData = senderData;
		this.gameId = gameId;
		this.turnPhaseIdx = turnPhaseIdx;
		this.turnPhase = turnPhase;
		this.currPlayerIdx = currPlayerIdx;
		this.tradeRecord = tradeRecord;
	}
	public WebsocketMessage() {
		
	}
}
