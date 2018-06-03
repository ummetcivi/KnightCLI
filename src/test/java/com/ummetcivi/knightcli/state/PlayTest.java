package com.ummetcivi.knightcli.state;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlayTest extends BaseStateTest {

    private State underTest = GameStates.PLAY;

    @Test
    public void shouldExecuteAndReturnTrue() {
        boolean execute = underTest.execute(stateContextMock, gameContextMock, gameIOMock);

        Assert.assertTrue(execute);
    }

    @Test
    public void shouldPrintOptions() {
        underTest.execute(stateContextMock, gameContextMock, gameIOMock);

        Mockito.verify(gameIOMock).printLine("1. Free farm");
        Mockito.verify(gameIOMock).printLine("2. Battle!");
    }


    @Test
    public void shouldSetPossibleStates() {
        underTest.execute(stateContextMock, gameContextMock, gameIOMock);

        Mockito.verify(stateContextMock).setPossibleCommands(Command.saveCommand(),
                Command.exitCommand(),
                Command.of("1", GameStates.FREE_FARM),
                Command.of("2", GameStates.BATTLE));
    }

}