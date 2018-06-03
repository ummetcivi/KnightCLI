package com.ummetcivi.knightcli.state;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SaveTest extends BaseStateTest {

    private State underTest = MenuStates.SAVE;

    @Test
    public void shouldSaveGameAndSetPossibleCommands() {
        boolean execute = underTest.execute(stateContextMock, gameContextMock, gameIOMock);

        Assert.assertTrue(execute);
        Mockito.verify(gameContextMock).saveGame();

        Mockito.verify(stateContextMock).setPossibleCommands(
                Command.of("1", GameStates.PLAY),
                Command.of("2", MenuStates.EXIT));
    }

}