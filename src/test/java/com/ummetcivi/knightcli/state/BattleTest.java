package com.ummetcivi.knightcli.state;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BattleTest extends BaseStateTest {

    private State underTest = GameStates.BATTLE;

    @Test
    public void shouldExecuteAndReturnTrue() {
        boolean execute = underTest.execute(stateContextMock, gameContextMock, gameIOMock);

        Assert.assertTrue(execute);
    }

    @Test
    public void shouldCreateRandomOpponentAndPrint() {
        underTest.execute(stateContextMock, gameContextMock, gameIOMock);

        Mockito.verify(gameContextMock).createRandomOpponent();
        Mockito.verify(gameContextMock).printWithOpponent(Mockito.any());

    }

    @Test
    public void shouldSetPossibleCommands() {
        underTest.execute(stateContextMock, gameContextMock, gameIOMock);

        Mockito.verify(stateContextMock).setPossibleCommands(
                Command.saveCommand(),
                Command.exitCommand(),
                Command.of("1", GameStates.FIGHT),
                Command.of("2", GameStates.BATTLE),
                Command.of("3", GameStates.PLAY));
    }

}