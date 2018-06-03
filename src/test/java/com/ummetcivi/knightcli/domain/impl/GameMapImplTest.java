package com.ummetcivi.knightcli.domain.impl;

import com.ummetcivi.knightcli.ReflectionUtils;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.function.BiConsumer;

public class GameMapImplTest {

    private GameMapImpl underTest = new GameMapImpl();

    @Test
    public void shouldReturnMobIndexWhenGivenCoordinateIsNotEmpty() {
        int x = 0;
        int y = 0;

        Mob[][] mobMatrix = (Mob[][]) ReflectionUtils.getField(underTest, "mobMatrix");
        mobMatrix[x][y] = Mob.APE;

        int mobIndex = underTest.pointOf(x, y);

        Assert.assertEquals(mobIndex, Mob.APE.ordinal());
    }

    @Test
    public void shouldPrintMap() {
        BiConsumer<String, Object[]> printFunction = Mockito.mock(BiConsumer.class);
        underTest.print(printFunction);

        Mockito.verify(printFunction, Mockito.atLeastOnce()).accept(Mockito.any(), Mockito.any());
    }

    @Test
    public void shouldPrintPlayerAsOWhenPlayerIsInMap() {
        int x = 2;
        int y = 3;

        ReflectionUtils.setField(underTest, "playerXIndex", x);
        ReflectionUtils.setField(underTest, "playerYIndex", y);

        BiConsumer<String, Object[]> printFunction = Mockito.mock(BiConsumer.class);
        underTest.print(printFunction);

        Mockito.verify(printFunction).accept("| %2s ", new Object[]{"O"});
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenCoordinatesAreOutOfIndex() {
        int x = -1;
        int y = 5;

        underTest.pointOf(x, y);
    }

    @Test
    public void shouldMoveAndChangePlayerCoordinatesWhenCoordinatesAreValid() {
        int x = 5;
        int y = 5;

        underTest.move(x, y);

        Assert.assertEquals((int) ReflectionUtils.getField(underTest, "playerXIndex"), x);
        Assert.assertEquals((int) ReflectionUtils.getField(underTest, "playerYIndex"), y);
    }
}