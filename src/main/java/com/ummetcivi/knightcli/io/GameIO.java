package com.ummetcivi.knightcli.io;

public interface GameIO {

    String readLine();

    void printLine(String message);

    void printWithFormat(String format, Object... args);

    void clear();
}
