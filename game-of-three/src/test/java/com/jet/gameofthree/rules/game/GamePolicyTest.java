package com.jet.gameofthree.rules.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.jet.gameofthree.rules.player.PlayerNumberPolicy;

@RunWith(MockitoJUnitRunner.class)
public class GamePolicyTest {

	private GamePolicy gamePolicy;

	@Mock
	private PlayerNumberPolicy playerNumberPolicy;

	@Test
	public void should_isOver_returnTrue_whenGameOverAtNumberIsAchieved() {
		gamePolicy = new GamePolicy(1, playerNumberPolicy);

		boolean isGameOver = gamePolicy.isOver(1);

		assertThat(isGameOver).isTrue();
	}

	@Test
	public void should_isOver_returnFalse_whenGameOverAtNumberIsNotYetAchieved() {
		gamePolicy = new GamePolicy(1, playerNumberPolicy);

		boolean isGameOver = gamePolicy.isOver(2);

		assertThat(isGameOver).isFalse();
	}

//	public void should_isReady_returnTrue_whenRequiredPlayerNumberIsAchieved() {
//		Mockito.when(playerNumberPolicy.isRequiredPlayerNumberAchieved()).thenReturn(true);
//
//		boolean isGameReady = gamePolicy.isReady();
//
//		assertThat(isGameReady).isTrue();
//	}
//
//	public void should_isReady_returnFalse_whenRequiredPlayerNumberIsNotYetAchieved() {
//		Mockito.when(playerNumberPolicy.isRequiredPlayerNumberAchieved()).thenReturn(false);
//
//		boolean isGameReady = gamePolicy.isReady();
//
//		assertThat(isGameReady).isFalse();
//	}
}
