package com.jet.gameofthree.player;

import com.jet.gameofthree.domain.Player;
import com.jet.gameofthree.domain.PlayerRepository;
import com.jet.gameofthree.rules.exceptions.PlayerCannotBeRegisteredException;
import com.jet.gameofthree.rules.player.PlayerPolicy;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PlayerManagerImplTest {

	@InjectMocks
	private PlayerManagerImpl playerManager;

	@Mock
	private PlayerRepository playerRepository;

	@Mock
	private PlayerPolicy playerPolicy;

	@Test
	public void save_player_whenThePlayerCanBeRegistered() {
		Player player = new Player("SHK");

		playerManager.add(player);

		verify(playerPolicy).checkThatThePlayerCanBeRegistered("SHK");
		verify(playerRepository).save(player);
	}

	@Test
	public void throwException_whenThePlayerCannotBeRegistered() {
		Player player = new Player("SHK");
		doThrow(PlayerCannotBeRegisteredException.class).when(playerPolicy).checkThatThePlayerCanBeRegistered("SHK");

		ThrowingRunnable runnable = () -> playerManager.add(player);

		assertThrows(PlayerCannotBeRegisteredException.class, runnable);
		verify(playerRepository, never()).save(player);
	}

	@Test
	public void reinit_players_deleteAllPlayers() {
		playerManager.reinitPlayers();

		verify(playerRepository).deleteAll();
	}
}
