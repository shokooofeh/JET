package com.jet.gameofthree.rules.player;

import static com.jet.gameofthree.rules.exceptions.PlayerCannotBeRegisteredReason.REQUIRED_PLAYER_NAME_USED;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.List;
import java.util.function.Predicate;

import com.jet.gameofthree.domain.Player;
import com.jet.gameofthree.rules.exceptions.PlayerCannotBeRegisteredException;
import com.jet.gameofthree.domain.PlayerRepository;


public class PlayerNamePolicy {

	private PlayerRepository playerRepository;

	public PlayerNamePolicy(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

	public void checkThatPlayerNameIsNotUsed(String playerName) {
		if (playerWithSameNameExists(playerName)) {
			throw new PlayerCannotBeRegisteredException(REQUIRED_PLAYER_NAME_USED);
		}
	}

	private boolean playerWithSameNameExists(String playerName) {
		List<Player> playerAlreadyRegistred = playerRepository.findAll();
		return !isEmpty(playerAlreadyRegistred)
				&& playerAlreadyRegistred.stream().filter(playerWithSameName(playerName)).findFirst().isPresent();
	}

	private Predicate<Player> playerWithSameName(String playerName) {
		return player -> player.getName().equals(playerName);
	}

}
