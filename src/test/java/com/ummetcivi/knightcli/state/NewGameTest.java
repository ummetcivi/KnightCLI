package com.ummetcivi.knightcli.state;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NewGameTest extends BaseStateTest {

    private State underTest = MenuStates.NEW_GAME;

    @Test
    public void shouldExecuteAndReturnTrue() {
        boolean execute = underTest.execute(stateContextMock, gameContextMock, gameIOMock);

        Assert.assertTrue(execute);
    }

    @Test
    public void shouldCreateGameAndSetPossibleCommands() {
        underTest.execute(stateContextMock, gameContextMock, gameIOMock);

        Mockito.verify(gameContextMock).createGame();

        Mockito.verify(stateContextMock).setPossibleCommands(
                Command.of("\\d", MenuStates.SELECT_CHARACTER),
                Command.exitCommand());
    }

}