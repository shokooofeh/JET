package com.jet.gameofthree.gamemanager;

import com.jet.gameofthree.domain.GameModeEnum;
import com.jet.gameofthree.domain.GameRound;
import com.jet.gameofthree.domain.GameState;

public interface GameManager {

	GameState playRound(GameRound gameRound);

	GameState addNewPlayer(String player, GameModeEnum gameMode);

}
