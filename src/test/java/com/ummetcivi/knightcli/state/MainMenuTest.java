package com.ummetcivi.knightcli.state;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MainMenuTest extends BaseStateTest {

    private State underTest = MenuStates.MAIN_MENU;

    @Test
    public void shouldExecuteAndReturnTrue() {
        boolean execute = underTest.execute(stateContextMock, gameContextMock, gameIOMock);

        Assert.assertTrue(execute);
    }

    @Test
    public void shouldPrintOptionsAndSetPossibleCommands() {
        underTest.execute(stateContextMock, gameContextMock, gameIOMock);

        Mockito.verify(gameIOMock).printLine("1. Create new game");
        Mockito.verify(gameIOMock).printLine("2. Load game\n");

        Mockito.verify(stateContextMock).setPossibleCommands(
                Command.of("1", MenuStates.NEW_GAME),
                Command.of("2", MenuStates.LOAD),
                Command.exitCommand());
    }

}