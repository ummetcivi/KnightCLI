package com.ummetcivi.knightcli.state;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SelectCharacterTest extends BaseStateTest {

    private State underTest = MenuStates.SELECT_CHARACTER;

    @Test
    public void shouldGetInputAndCreateCharacterAndSetNextStateAsPlay() {
        Mockito.when(stateContextMock.getCurrentInput()).thenReturn("2");

        boolean execute = underTest.execute(stateContextMock, gameContextMock, gameIOMock);

        Mockito.verify(gameContextMock).createCharacter("2");
        Mockito.verify(gameContextMock).printPlayer(Mockito.any());
        Mockito.verify(stateContextMock).setNextState(GameStates.PLAY);
        Assert.assertTrue(execute);
    }

}