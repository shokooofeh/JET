package com.jet.gameofthree.rules.player;

import com.jet.gameofthree.domain.Player;
import com.jet.gameofthree.domain.PlayerRepository;
import com.jet.gameofthree.rules.exceptions.PlayerCannotBeRegisteredException;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlayerNumberPolicyTest {

	private PlayerNumberPolicy playerNumberPolicy;

	@Mock
	private PlayerRepository playerRepository;

	@Test
	public void throwException_whenNoOtherPlayIsRequired() {
		playerNumberPolicy = new PlayerNumberPolicy(playerRepository, 2);
		when(playerRepository.findAll()).thenReturn(asList(new Player("name1"), new Player("name2")));

		ThrowingRunnable runnable = () -> playerNumberPolicy.checkThatOtherPlayerIsRequired();

		assertThrows("A new player cannot be registered because Required player number is already achieved.",
				PlayerCannotBeRegisteredException.class, runnable);
	}

	@Test
	public void doesNotThrowException_whenOtherPlayIsRequired() {
		playerNumberPolicy = new PlayerNumberPolicy(playerRepository, 3);
		when(playerRepository.findAll()).thenReturn(asList(new Player("name1"), new Player("name2")));

		playerNumberPolicy.checkThatOtherPlayerIsRequired();
	}

	@Test
	public void return_true_whenRequiredPlayerNumberIsAchieved() {
		playerNumberPolicy = new PlayerNumberPolicy(playerRepository, 2);
		when(playerRepository.findAll()).thenReturn(asList(new Player("name1"), new Player("name2")));

		boolean isRequiredPlayerNumberAchieved = playerNumberPolicy.isRequiredPlayerNumberAchieved();

		assertThat(isRequiredPlayerNumberAchieved).isTrue();
	}

	@Test
	public void return_false_whenRequiredPlayerNumberIsNotAchieved() {
		playerNumberPolicy = new PlayerNumberPolicy(playerRepository, 3);
		when(playerRepository.findAll()).thenReturn(asList(new Player("name1"), new Player("name2")));

		boolean isRequiredPlayerNumberAchieved = playerNumberPolicy.isRequiredPlayerNumberAchieved();

		assertThat(isRequiredPlayerNumberAchieved).isFalse();
	}

}
