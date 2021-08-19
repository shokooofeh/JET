package com.jet.gameofthree.rules.player;

import static com.jet.gameofthree.rules.exceptions.PlayerCannotBeRegisteredReason.REQUIRED_PLAYER_NUMBER_ACHIEVED;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.List;

import com.jet.gameofthree.domain.Player;
import com.jet.gameofthree.rules.exceptions.PlayerCannotBeRegisteredException;
import com.jet.gameofthree.domain.PlayerRepository;

public class PlayerNumberPolicy {

	private PlayerRepository playerRepository;

	private int requiredPlayerNumberToStart;

	public PlayerNumberPolicy(PlayerRepository playerRepository, int requiredPlayerNumberToStart) {
		this.playerRepository = playerRepository;
		this.requiredPlayerNumberToStart = requiredPlayerNumberToStart;
	}

	public void checkThatOtherPlayerIsRequired() {
		if (isRequiredPlayerNumberAchieved())
			throw new PlayerCannotBeRegisteredException(REQUIRED_PLAYER_NUMBER_ACHIEVED);
	}

	public boolean isRequiredPlayerNumberAchieved() {
		List<Player> playerAlreadyRegistered = playerRepository.findAll();
		return !isEmpty(playerAlreadyRegistered) && playerAlreadyRegistered.size() >= requiredPlayerNumberToStart;
	}

}
