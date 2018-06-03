package com.ummetcivi.knightcli.domain.impl;

import com.ummetcivi.knightcli.ReflectionUtils;
import com.ummetcivi.knightcli.domain.GameMap;
import com.ummetcivi.knightcli.io.GameIO;
import com.ummetcivi.knightcli.storage.ContextStorage;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GameContextImplTest {

    @InjectMocks
    private GameContextImpl underTest;
    @Mock
    private ContextStorage contextStorageMock;
    @Mock
    private GameIO gameIOMock;
    @Mock
    private GameMap gameMapMock;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCreateGame() {
        underTest.createGame();

        Assert.assertNotNull(underTest.game);
    }

    @Test
    public void shouldLoadGameFromStorage() {
        Mockito.when(contextStorageMock.load(Mockito.anyString())).thenReturn(underTest);

        underTest.loadGame();

        Assert.assertNotNull(underTest.game);
    }

    @Test(dependsOnMethods = "shouldCreateGame")
    public void shouldSaveGameToStorage() {
        underTest.saveGame();

        Mockito.verify(contextStorageMock).save(Mockito.eq(underTest), Mockito.anyString());
    }

    @Test(dependsOnMethods = "shouldCreateGame")
    public void shouldCreateRandomOpponent() {
        underTest.createRandomOpponent();
    }

    @Test(dependsOnMethods = "shouldCreateGame")
    public void shouldCreateCharacter() {
        underTest.createCharacter("1");
    }

    @Test(dependsOnMethods = {"shouldCreateGame", "shouldCreateCharacter", "shouldCreateRandomOpponent"})
    public void shouldFight() {
        ReflectionUtils.setField(underTest, "delayInMilliseconds", 1);

        underTest.fight(gameIOMock::printWithFormat);
    }

    @Test(dependsOnMethods = {"shouldCreateGame", "shouldCreateCharacter"})
    public void shouldPrintFight() {
        underTest.printWithOpponent(gameIOMock::printWithFormat);
    }

    @Test
    public void shouldSetOpponentAndReturnTrueWhenGivenCoordinateIsNotEmpty() {
        int x = 1;
        int y = 2;
        int point = 1;
        Mob mob = Mob.getByIndex(1);

        Mockito.when(gameMapMock.pointOf(x, y)).thenReturn(point);

        boolean b = underTest.goTo(x, y);

        Assert.assertTrue(b);

    }
}