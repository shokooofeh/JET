package com.jet.gameofthree.gamemanager;

import static com.jet.gameofthree.domain.GameModeEnum.AI;
import static com.jet.gameofthree.domain.GameModeEnum.HUMAN;
import static com.jet.gameofthree.domain.GameStateEnum.IN_PROGRESS;
import static com.jet.gameofthree.domain.GameStateEnum.OVER;
import static com.jet.gameofthree.domain.GameStateEnum.WAITING_FOR_PLAYER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.jet.gameofthree.gamemanager.GameManagerImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.jet.gameofthree.domain.GameRound;
import com.jet.gameofthree.domain.GameState;
import com.jet.gameofthree.player.PlayerManager;
import com.jet.gameofthree.rules.game.GamePolicy;
import com.jet.gameofthree.rules.player.PlayerNumberPolicy;

@RunWith(MockitoJUnitRunner.class)
public class GameManagerImplTest {

	private GameManagerImpl gameManager;

	@Mock
	private PlayerManager playerManager;

	@Mock
	private GamePolicy gamePolicy;

	@Mock
	private PlayerNumberPolicy playerNumberPolicy;

	@Test
	public void should_playRound_returnGameStateInProgress_whenItIsNotOver() {
		gameManager = new GameManagerImpl(playerManager, gamePolicy, playerNumberPolicy, 30, 10);
		GameRound gameRound = new GameRound(10, "SHK", -1, HUMAN);
		when(gamePolicy.isOver(3)).thenReturn(false);

		GameState gameState = gameManager.playRound(gameRound);

		assertThat(gameState.getCurrentNumber()).isEqualTo(3);
		assertThat(gameState.getLastNumberAdded()).isEqualTo(-1);
		assertThat(gameState.getLastPlayer()).isEqualTo("SHK");
		assertThat(gameState.getState()).isEqualTo(IN_PROGRESS);
	}

	@Test
	public void should_playRound_returnGameStateOver_whenItIsOver() {
		gameManager = new GameManagerImpl(playerManager, gamePolicy, playerNumberPolicy, 30, 10);
		GameRound gameRound = new GameRound(3, "SHK", 0, AI);
		when(gamePolicy.isOver(1)).thenReturn(true);
		GameState gameState = gameManager.playRound(gameRound);

		assertThat(gameState.getCurrentNumber()).isEqualTo(1);
		assertThat(gameState.getLastNumberAdded()).isEqualTo(0);
		assertThat(gameState.getLastPlayer()).isEqualTo("SHK");
		assertThat(gameState.getState()).isEqualTo(OVER);
	}

	@Test
	public void should_addNewPlayer_returnGameStateWaitingForPlayer_whenRequiredPlayerNumberIsNotAchieved() {
		gameManager = new GameManagerImpl(playerManager, gamePolicy, playerNumberPolicy, 30, 10);
		when(playerNumberPolicy.isRequiredPlayerNumberAchieved()).thenReturn(false);

		GameState gameState = gameManager.addNewPlayer("SHK", HUMAN);

		assertThat(gameState.getState()).isEqualTo(WAITING_FOR_PLAYER);
		assertThat(gameState.getCurrentNumber()).isNull();
		assertThat(gameState.getLastNumberAdded()).isNull();
		assertThat(gameState.getLastPlayer()).isEqualTo("SHK");
	}

	@Test
	public void should_addNewPlayer_returnGameStateInProgress_whenRequiredPlayerNumberIsAchieved() {
		gameManager = new GameManagerImpl(playerManager, gamePolicy, playerNumberPolicy, 30, 10);
		when(playerNumberPolicy.isRequiredPlayerNumberAchieved()).thenReturn(true);

		GameState gameState = gameManager.addNewPlayer("SHK", HUMAN);

		assertThat(gameState.getState()).isEqualTo(IN_PROGRESS);
		assertThat(gameState.getCurrentNumber()).isNotNull();
		assertThat(gameState.getLastNumberAdded()).isNull();
		assertThat(gameState.getLastPlayer()).isEqualTo("SHK");
	}

}
