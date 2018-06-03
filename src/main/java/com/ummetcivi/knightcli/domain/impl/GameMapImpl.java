package com.ummetcivi.knightcli.domain.impl;

import com.ummetcivi.knightcli.domain.GameMap;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

public class GameMapImpl implements GameMap {

    private static final long serialVersionUID = -2263747038655781061L;
    private int width;
    private int height;
    private Mob[][] mobMatrix;

    private int playerXIndex = -1;
    private int playerYIndex = -1;

    GameMapImpl() {
        this.width = 15;
        this.height = 10;
        mobMatrix = new Mob[height][width];
    }

    private void checkMove(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y > height) {
            throw new IllegalArgumentException("You cannot move there!");
        }
    }

    @Override
    public int pointOf(int x, int y) {
        checkMove(x, y);

        return mobMatrix[y][x] == null ? -1 : mobMatrix[y][x].ordinal();
    }

    @Override
    public void print(BiConsumer<String, Object[]> printFunction) {
        int width = mobMatrix[0].length;

        printFunction.accept(" %3s", new Object[]{""});
        IntStream.range(0, width).forEach(i -> printFunction.accept("| %2s ", new Object[]{String.valueOf(i)}));
        printFunction.accept("|\n", new Object[]{});

        IntStream.range(0, mobMatrix.length).forEach(i -> {
            Mob[] mobArray = mobMatrix[i];
            printFunction.accept("%3d ", new Object[]{i});

            IntStream.range(0, mobArray.length).forEach(j -> {
                if (j == playerXIndex && i == playerYIndex) {
                    printFunction.accept("| %2s ", new Object[]{mobArray[j] != null ? "XO" : "O"});
                } else {
                    printFunction.accept("| %2s ", new Object[]{mobArray[j] != null ? "X" : " "});
                }
            });
            printFunction.accept("|", new Object[]{});
            printFunction.accept("\n%4s", new Object[]{" "});
            Arrays.stream(mobArray).forEach(mob -> printFunction.accept("| %2s ", new Object[]{"--"}));
            printFunction.accept("|\n", new Object[]{});
        });
    }

    @Override
    public void move(int x, int y) {
        checkMove(x, y);
        playerXIndex = x;
        playerYIndex = y;

    }

    void fill(BiFunction<Integer, Integer, Integer> randomFunction) {
        Arrays.stream(Mob.values()).forEach(mob -> {
            int randomX = randomFunction.apply(0, width);
            int randomY = randomFunction.apply(0, height);
            mobMatrix[randomY][randomX] = mob;
        });
    }


}