package com.ummetcivi.knightcli.state;

import com.ummetcivi.knightcli.domain.impl.FightStatus;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FightTest extends BaseStateTest {

    private State underTest = GameStates.FIGHT;

    @Test
    public void shouldCallFightAndPrintResult() {

        Mockito.when(gameContextMock.fight(Mockito.any())).thenReturn(FightStatus.WIN);
        boolean execute = underTest.execute(stateContextMock, gameContextMock, gameIOMock);

        Mockito.verify(gameIOMock).printWithFormat("You've %s the fight\n", FightStatus.WIN.toString());
        Mockito.verify(gameContextMock).printPlayer(Mockito.any());
        Assert.assertTrue(execute);
    }

    @Test
    public void shouldSetPossibleCommands() {
        Mockito.when(gameContextMock.fight(Mockito.any())).thenReturn(FightStatus.WIN);
        underTest.execute(stateContextMock, gameContextMock, gameIOMock);

        Mockito.verify(stateContextMock).setPossibleCommands(
                Command.of(".*", GameStates.PLAY));
    }

}