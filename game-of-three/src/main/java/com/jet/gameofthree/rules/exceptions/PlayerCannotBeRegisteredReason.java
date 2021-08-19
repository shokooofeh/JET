package com.jet.gameofthree.rules.exceptions;

public enum PlayerCannotBeRegisteredReason {
	REQUIRED_PLAYER_NUMBER_ACHIEVED("Required player number is already achieved"),
	REQUIRED_PLAYER_NAME_USED("Player name is already used");

	private String message;

	private PlayerCannotBeRegisteredReason(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
