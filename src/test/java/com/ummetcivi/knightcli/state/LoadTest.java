package com.ummetcivi.knightcli.state;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoadTest extends BaseStateTest {

    private State underTest = MenuStates.LOAD;

    @Test
    public void shouldLoadGameAndSetNextStateAsPlay() {
        boolean execute = underTest.execute(stateContextMock, gameContextMock, gameIOMock);

        Assert.assertTrue(execute);
        Mockito.verify(gameContextMock).loadGame();
        Mockito.verify(stateContextMock).setNextState(GameStates.PLAY);
    }

}