package com.ummetcivi.knightcli.state;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FreeFarmTest extends BaseStateTest {

    private State underTest = GameStates.FREE_FARM;

    @Test
    public void shouldExecuteAndPrintMapIfInputDoesNotContainComma() {

        Mockito.when(stateContextMock.getCurrentInput()).thenReturn("1");
        boolean execute = underTest.execute(stateContextMock, gameContextMock, gameIOMock);

        Assert.assertTrue(execute);
        Mockito.verify(gameContextMock).printMap(Mockito.any());
        Mockito.verify(stateContextMock).setPossibleCommands(
                Command.saveCommand(),
                Command.exitCommand(),
                Command.of("\\d+,\\d+", GameStates.FREE_FARM));
    }

    @Test
    public void shouldPrintOpponentAndSetPossibleCommandsWhenMobExistOnGivenCoordinates() {
        Mockito.when(stateContextMock.getCurrentInput()).thenReturn("2,3");
        Mockito.when(gameContextMock.goTo(2, 3)).thenReturn(true);
        underTest.execute(stateContextMock, gameContextMock, gameIOMock);

        Mockito.verify(gameContextMock).goTo(2, 3);
        Mockito.verify(gameContextMock).printWithOpponent(Mockito.any());
        Mockito.verify(stateContextMock).setPossibleCommands(
                Command.saveCommand(),
                Command.exitCommand(),
                Command.of("1", GameStates.FIGHT),
                Command.of("2", GameStates.FREE_FARM),
                Command.of("3", GameStates.PLAY));

    }

    @Test
    public void shouldPrintMapAndSetCurrentCommandPossibleWhenGivenCoordinatesIsEmpty() {
        Mockito.when(stateContextMock.getCurrentInput()).thenReturn("2,3");
        Mockito.when(gameContextMock.goTo(2, 3)).thenReturn(false);
        underTest.execute(stateContextMock, gameContextMock, gameIOMock);

        Mockito.verify(gameContextMock).goTo(2, 3);
        Mockito.verify(gameContextMock).printMap(Mockito.any());
        Mockito.verify(stateContextMock).setPossibleCommands(
                Command.saveCommand(),
                Command.exitCommand(),
                Command.of("\\d+,\\d+", GameStates.FREE_FARM));
    }

    @Test
    public void shouldPrintMapAndSetCurrentCommandPossibleWhenExceptionThrown() {
        Mockito.when(stateContextMock.getCurrentInput()).thenReturn("2,3");
        Mockito.when(gameContextMock.goTo(2, 3)).thenThrow(IllegalArgumentException.class);

        underTest.execute(stateContextMock, gameContextMock, gameIOMock);

        Mockito.verify(gameContextMock).goTo(2, 3);
        Mockito.verify(gameContextMock).printMap(Mockito.any());
        Mockito.verify(stateContextMock).setPossibleCommands(
                Command.saveCommand(),
                Command.exitCommand(),
                Command.of("\\d+,\\d+", GameStates.FREE_FARM));
    }

}