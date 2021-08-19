package com.jet.gameofthree.player;

import com.jet.gameofthree.domain.Player;
import com.jet.gameofthree.domain.PlayerRepository;
import com.jet.gameofthree.rules.player.PlayerPolicy;

public class PlayerManagerImpl implements PlayerManager {

	private PlayerRepository playerRepository;

	private PlayerPolicy playerPolicy;

	public PlayerManagerImpl(PlayerRepository playerRepository, PlayerPolicy playerPolicy) {
		this.playerRepository = playerRepository;
		this.playerPolicy = playerPolicy;
	}

	@Override
	public void add(Player player) {
		playerPolicy.checkThatThePlayerCanBeRegistered(player.getName());
		playerRepository.save(player);
	}

	@Override
	public void reinitPlayers() {
		playerRepository.deleteAll();
	}

	public boolean isAPlayerWaiting() {
		return playerRepository.findAll().size() == 1;
	}

}
