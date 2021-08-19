package com.jet.gameofthree.player;

import com.jet.gameofthree.domain.Player;

public interface PlayerManager {

	void add(Player player);

	void reinitPlayers();

	boolean isAPlayerWaiting();
}
