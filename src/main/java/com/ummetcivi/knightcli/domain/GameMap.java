package com.ummetcivi.knightcli.domain;

import java.io.Serializable;
import java.util.function.BiConsumer;

public interface GameMap extends Serializable {

    int pointOf(int x, int y);

    void print(BiConsumer<String, Object[]> printFunction);

    void move(int x, int y);
}
