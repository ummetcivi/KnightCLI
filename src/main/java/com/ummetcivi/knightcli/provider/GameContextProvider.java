package com.ummetcivi.knightcli.provider;

import com.ummetcivi.knightcli.domain.GameContext;
import com.ummetcivi.knightcli.domain.impl.GameContextImpl;

public class GameContextProvider {

    public static GameContext currentContext() {
        return GameContextImpl.currentContext();
    }
}
