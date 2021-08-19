package com.jet.gameofthree.rules.exceptions;

import static java.lang.String.format;

public class PlayerCannotBeRegisteredException extends RuntimeException {

	private static final long serialVersionUID = -6945847183138381340L;

	private static final String REGISTRATION_ERROR_MESSAGE = "A new player cannot be registered because %s.";

	public PlayerCannotBeRegisteredException(PlayerCannotBeRegisteredReason reason) {
		super(format(REGISTRATION_ERROR_MESSAGE, reason.getMessage()));
	}

}
