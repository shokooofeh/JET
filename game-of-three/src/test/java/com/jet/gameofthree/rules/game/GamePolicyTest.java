package com.jet.gameofthree.rules.game;

import com.jet.gameofthree.rules.player.PlayerNumberPolicy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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

}
