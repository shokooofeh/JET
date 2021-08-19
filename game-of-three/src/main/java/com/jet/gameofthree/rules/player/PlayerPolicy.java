package com.jet.gameofthree.rules.player;


public class PlayerPolicy {
	private PlayerNumberPolicy playerNumberPolicy;

	private PlayerNamePolicy playerNamePolicy;

	public PlayerPolicy(PlayerNumberPolicy playerNumberPolicy, PlayerNamePolicy playerNamePolicy) {
		this.playerNumberPolicy = playerNumberPolicy;
		this.playerNamePolicy = playerNamePolicy;
	}

	public void checkThatThePlayerCanBeRegistered(String playerName) {
		playerNumberPolicy.checkThatOtherPlayerIsRequired();
		playerNamePolicy.checkThatPlayerNameIsNotUsed(playerName);
	}

}
