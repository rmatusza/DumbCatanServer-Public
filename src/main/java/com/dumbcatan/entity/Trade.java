package com.dumbcatan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dumbcatan.entity.http.ResponseEntityHelper;

@Entity
@Table(name="trades")
public class Trade extends ResponseEntityHelper{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="trade_id")
	private int tradeId;
	
	@Column(name="trades_game_id")
	private int tradesGameId;
	
	@Column(name="trades_sender_id")
	private int tradesSenderId;
	
	@Column(name="trades_recipient_id")
	private int tradesRecipientId;
	
	@Column(name="offered_resources")
	private String offeredResources;
	
	@Column(name="desired_resources")
	private String desiredResources;
	
	@Column(name="trade_status")
	private int tradeStatus = 0;
	
	@Column(name="sender_data")
	private String senderData;
	
	public String getSenderData() {
		return senderData;
	}

	public void setSenderData(String senderData) {
		this.senderData = senderData;
	}

	private boolean tradeFound = true;
	
	public boolean getTradeFound() {
		return tradeFound;
	}

	public void setTradeFound(boolean tradeFound) {
		this.tradeFound = tradeFound;
	}

	public int getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(int tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public int getTradesGameId() {
		return tradesGameId;
	}

	public void setTradesGameId(int tradesGameId) {
		this.tradesGameId = tradesGameId;
	}

	public int getTradeId() {
		return tradeId;
	}

	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}

	public int getTradesSenderId() {
		return tradesSenderId;
	}

	public void setTradesSenderId(int tradesSenderId) {
		this.tradesSenderId = tradesSenderId;
	}

	public int getTradesRecipientId() {
		return tradesRecipientId;
	}

	public void setTradesRecipientId(int tradesRecipientId) {
		this.tradesRecipientId = tradesRecipientId;
	}

	public String getOfferedResources() {
		return offeredResources;
	}

	public void setOfferedResources(String offeredResources) {
		this.offeredResources = offeredResources;
	}

	public String getDesiredResources() {
		return desiredResources;
	}

	public void setDesiredResources(String desiredResources) {
		this.desiredResources = desiredResources;
	}
	
	public Trade() {
		
	}

	public Trade(int tradesGameId, int tradesSenderId, int tradesRecipientId, String offeredResources,
			String desiredResources, String senderData) {
		this.tradesGameId = tradesGameId;
		this.tradesSenderId = tradesSenderId;
		this.tradesRecipientId = tradesRecipientId;
		this.offeredResources = offeredResources;
		this.desiredResources = desiredResources;
		this.senderData = senderData;
	}
}
