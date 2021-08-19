package com.jet.gameofthree.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jet.gameofthree.gamemanager.GameManager;
import com.jet.gameofthree.gamemanager.GameManagerImpl;
import com.jet.gameofthree.player.PlayerManager;
import com.jet.gameofthree.player.PlayerManagerImpl;
import com.jet.gameofthree.domain.PlayerRepository;
import com.jet.gameofthree.rules.game.GamePolicy;
import com.jet.gameofthree.rules.player.PlayerNamePolicy;
import com.jet.gameofthree.rules.player.PlayerNumberPolicy;
import com.jet.gameofthree.rules.player.PlayerPolicy;

@Configuration
public class GameOfThreeConfiguration {

	@Bean
	public GameManager gameManager(PlayerManager playerManager, GamePolicy gamePolicy,
			PlayerNumberPolicy playerNumberPolicy, @Value("${gameofthree.game.min-number:2}") int minNumber,
			@Value("${gameofthree.game.max-number:1000}") int maxNumber) {
		return new GameManagerImpl(playerManager, gamePolicy, playerNumberPolicy, maxNumber, minNumber);
	}

	@Bean
	public PlayerManager playerManager(PlayerRepository playerRepository, PlayerPolicy playerPolicy) {
		return new PlayerManagerImpl(playerRepository, playerPolicy);
	}

	@Bean
	public GamePolicy gamePolicy(@Value("${gameofthree.game.over-at-number:1}") int gameOverAtNumber,
			PlayerNumberPolicy playerNumberPolicy) {
		return new GamePolicy(gameOverAtNumber, playerNumberPolicy);
	}

	@Bean
	public PlayerPolicy playerPolicy(PlayerNumberPolicy playerNumberPolicy, PlayerNamePolicy playerNamePolicy) {
		return new PlayerPolicy(playerNumberPolicy, playerNamePolicy);
	}

	@Bean
	public PlayerNamePolicy playerNamePolicy(PlayerRepository playerRepository) {
		return new PlayerNamePolicy(playerRepository);
	}

	@Bean
	public PlayerNumberPolicy playerNumberPolicy(PlayerRepository playerRepository,
			@Value("${gameofthree.player.required-number:2}") int requiredPlayerNumberToStart) {
		return new PlayerNumberPolicy(playerRepository, requiredPlayerNumberToStart);
	}

}
