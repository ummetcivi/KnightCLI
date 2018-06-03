package com.ummetcivi.knightcli.provider;

import com.ummetcivi.knightcli.io.GameIO;
import com.ummetcivi.knightcli.io.impl.ConsoleIO;

public class GameIOProvider {

    public static GameIO consoleIO() {
        return ConsoleIO.getInstance();
    }
}
