package com.jet.gameofthree.rules.player;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.jet.gameofthree.domain.Player;
import com.jet.gameofthree.rules.exceptions.PlayerCannotBeRegisteredException;
import com.jet.gameofthree.domain.PlayerRepository;

@RunWith(MockitoJUnitRunner.class)
public class PlayerNamePolicyTest {

	@InjectMocks
	private PlayerNamePolicy playerNamePolicy;

	@Mock
	private PlayerRepository playerRepository;

	@Test
	public void should_checkThatPlayerNameIsNotUsed_throwException_whenPlayerNameIsAlreadyUsed() {
		when(playerRepository.findAll()).thenReturn(asList(new Player("name")));

		ThrowingRunnable runnable = () -> playerNamePolicy.checkThatPlayerNameIsNotUsed("name");

		assertThrows("A new player cannot be registered because Player name is already used.",
				PlayerCannotBeRegisteredException.class, runnable);
	}

	@Test
	public void should_checkThatPlayerNameIsNotUsed_doesntThrowException_whenPlayerNameIsNotUsed() {
		when(playerRepository.findAll()).thenReturn(asList(new Player("name")));

		playerNamePolicy.checkThatPlayerNameIsNotUsed("other_name");

	}
}
