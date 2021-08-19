package com.jet.gameofthree.rules.game;

import com.jet.gameofthree.rules.player.PlayerNumberPolicy;

public class GamePolicy {

	private int gameOverAtNumber;

	private PlayerNumberPolicy playerNumberPolicy;

	public GamePolicy(int gameOverAtNumber, PlayerNumberPolicy playerNumberPolicy) {
		this.gameOverAtNumber = gameOverAtNumber;
		this.playerNumberPolicy = playerNumberPolicy;
	}

	public boolean isOver(int currentNumber) {
		return currentNumber == gameOverAtNumber;
	}

	public boolean isReady() {
		return playerNumberPolicy.isRequiredPlayerNumberAchieved();
	}

}
