package com.ummetcivi.knightcli.domain;

import com.ummetcivi.knightcli.domain.impl.FightStatus;

import java.io.Serializable;
import java.util.function.BiConsumer;

public interface GameContext extends Serializable {

    void createGame();

    void loadGame();

    void saveGame();

    void createRandomOpponent();

    void createCharacter(String characterId);

    FightStatus fight(BiConsumer<String, Object[]> printFunction);

    void printPlayer(BiConsumer<String, Object[]> printFunction);

    void printWithOpponent(BiConsumer<String, Object[]> printFunction);

    void printMap(BiConsumer<String, Object[]> printFunction);

    boolean goTo(int x, int y);
}
