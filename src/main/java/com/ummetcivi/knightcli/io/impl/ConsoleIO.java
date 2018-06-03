package com.ummetcivi.knightcli.io.impl;

import com.ummetcivi.knightcli.io.GameIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleIO implements GameIO {

    private static GameIO instance;
    private final BufferedReader consoleReader;

    private ConsoleIO() {
        consoleReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static GameIO getInstance() {
        if (instance == null) {
            instance = new ConsoleIO();
        }
        return instance;
    }

    @Override
    public String readLine() {
        try {
            return consoleReader.readLine();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void printLine(String message) {
        System.out.println(message);
    }

    @Override
    public void clear() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            try {
                Runtime.getRuntime().exec("clear");
            } catch (IOException e1) {
                //no need
            }
        }
    }

    @Override
    public void printWithFormat(String format, Object... args) {
        System.out.printf(format, args);
    }
/*
    @Override
    public void printCharacterStatus(Character character) {
        String leftAlignFormat = "| %-15s | %-15s | %n";
        String leftAlignFormatForExp = "| %-30s    | %n";
        System.out.format("+-----------Your Character----------+%n");
        System.out.printf(leftAlignFormat, rate("HP", character.getHp(), character.maxHp()),
                stat("Attack P", character.getAttackPower()));
        System.out.printf(leftAlignFormat, stat("Level", character.getLevel()),
                stat("Defense", character.getDefence()));
        System.out.printf(leftAlignFormatForExp, rate("Exp", character.getExp(), character.maxExperience()));
        System.out.format("+-----------------+-----------------+%n");
    }

    @Override
    public void printFight(Character player, Character opponent) {

    }*/


}
