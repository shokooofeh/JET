package com.jet.gameofthree.rules.game;

import com.jet.gameofthree.rules.player.PlayerNumberPolicy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class GamePolicyTest {

    private GamePolicy gamePolicy;

    @Mock
    private PlayerNumberPolicy playerNumberPolicy;

    @Test
    public void return_true_whenGameOverAtNumberIsAchieved() {
        gamePolicy = new GamePolicy(1, playerNumberPolicy);

        boolean isGameOver = gamePolicy.isOver(1);

        assertThat(isGameOver).isTrue();
    }

    @Test
    public void return_false_whenGameOverAtNumberIsNotYetAchieved() {
        gamePolicy = new GamePolicy(1, playerNumberPolicy);

        boolean isGameOver = gamePolicy.isOver(2);

        assertThat(isGameOver).isFalse();
    }

    @Test
    public void return_true_whenRequiredPlayerNumberIsAchieved() {
        gamePolicy = new GamePolicy(1, playerNumberPolicy);
        Mockito.when(playerNumberPolicy.isRequiredPlayerNumberAchieved()).thenReturn(true);

        boolean isGameReady = gamePolicy.isReady();

        assertThat(isGameReady).isTrue();
    }

    @Test
    public void return_false_whenRequiredPlayerNumberIsNotYetAchieved() {
        gamePolicy = new GamePolicy(1, playerNumberPolicy);
        Mockito.when(playerNumberPolicy.isRequiredPlayerNumberAchieved()).thenReturn(false);

        boolean isGameReady = gamePolicy.isReady();

        assertThat(isGameReady).isFalse();
    }
}
