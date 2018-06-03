package com.ummetcivi.knightcli.storage;


import com.ummetcivi.knightcli.domain.GameContext;

public interface ContextStorage {

    void save(GameContext game, String name);

    GameContext load(String name);
}
