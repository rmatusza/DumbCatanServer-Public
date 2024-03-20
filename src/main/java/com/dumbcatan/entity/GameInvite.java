package com.dumbcatan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dumbcatan.entity.http.ResponseEntityHelper;

@Entity
@Table(name="game_invites")
public class GameInvite extends ResponseEntityHelper{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="game_invite_id")
	private int gameInviteId;
	
	@Column(name="recipient_id")
	private int recipientId;
	
	@Column(name="sender_id")
	private int senderId;
	
	@Column(name="game_id")
	private int gameId;
	
	@Column(name="accepted")
	private int accepted;

	public int getAccepted() {
		return accepted;
	}

	public void setAccepted(int accepted) {
		this.accepted = accepted;
	}

	public int getGameInviteId() {
		return gameInviteId;
	}

	public void setGameInviteId(int gameInviteId) {
		this.gameInviteId = gameInviteId;
	}


	public int getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(int recipientId) {
		this.recipientId = recipientId;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	
	
	public GameInvite() {
		
	}

	public GameInvite(int recipientId, int senderId, int gameId, int accepted) {
		this.recipientId = recipientId;
		this.senderId = senderId;
		this.gameId = gameId;
		this.accepted = accepted;
	}

}
