package com.jet.gameofthree.controller;

import static com.jet.gameofthree.domain.GameModeEnum.AI;
import static com.jet.gameofthree.domain.GameModeEnum.HUMAN;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.jet.gameofthree.dto.GameParticipateRequest;

public class GameOfThreeHelperTest {

	@Test
	public void return_true_whenGameModeIsAiAndPlayerNameIsNotAi() {
		GameParticipateRequest gameParticipateRequest = new GameParticipateRequest("SHK", AI);
		String aiPlayerName = "Ai";
		boolean shouldAddAiPlayer = GameOfThreeHelper.shouldAddAiPlayer(gameParticipateRequest, aiPlayerName);
		assertThat(shouldAddAiPlayer).isTrue();
	}

	@Test
	public void return_false_whenGameModeIsAiAndPlayerNameIsAi() {
		String aiPlayerName = "Ai";
		GameParticipateRequest gameParticipateRequest = new GameParticipateRequest(aiPlayerName, AI);
		boolean shouldAddAiPlayer = GameOfThreeHelper.shouldAddAiPlayer(gameParticipateRequest, aiPlayerName);
		assertThat(shouldAddAiPlayer).isFalse();
	}

	@Test
	public void return_false_whenGameModeIsHuman() {
		String aiPlayerName = "Ai";
		GameParticipateRequest gameParticipateRequest = new GameParticipateRequest("SHK", HUMAN);
		boolean shouldAddAiPlayer = GameOfThreeHelper.shouldAddAiPlayer(gameParticipateRequest, aiPlayerName);
		assertThat(shouldAddAiPlayer).isFalse();
	}

}
