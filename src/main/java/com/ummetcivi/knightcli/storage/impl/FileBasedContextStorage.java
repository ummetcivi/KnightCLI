package com.ummetcivi.knightcli.storage.impl;

import com.ummetcivi.knightcli.domain.GameContext;
import com.ummetcivi.knightcli.storage.ContextStorage;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileBasedContextStorage implements ContextStorage {

    private static FileBasedContextStorage instance;

    private FileBasedContextStorage() {
    }

    public static ContextStorage getInstance() {
        if (instance == null) {
            instance = new FileBasedContextStorage();
        }
        return instance;
    }

    @Override
    public void save(GameContext game, String name) {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(name));
                ObjectOutputStream oos = new ObjectOutputStream(outputStream)) {
            oos.writeObject(game);
        } catch (IOException e) {
            throw new IllegalArgumentException("Couldn't save game! Please try again");
        }
    }

    @Override
    public GameContext load(String name) {
        try (InputStream inputStream = Files.newInputStream(Paths.get(name));
                ObjectInputStream ois = new ObjectInputStream(inputStream)) {

            return (GameContext) ois.readObject();
        } catch (Exception e) {
            throw new IllegalArgumentException("Couldn't load game from file! Please try again!");
        }
    }
}
