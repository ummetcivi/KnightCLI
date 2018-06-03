package com.ummetcivi.knightcli.state;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ExitTest extends BaseStateTest {

    private State underTest = MenuStates.EXIT;

    @Test
    public void shouldExecuteAndReturnFalse() {
        boolean execute = underTest.execute(stateContextMock, gameContextMock, gameIOMock);

        Assert.assertFalse(execute);
    }

}