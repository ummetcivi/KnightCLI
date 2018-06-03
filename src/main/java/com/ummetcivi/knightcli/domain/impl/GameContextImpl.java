package com.ummetcivi.knightcli.domain.impl;

import com.ummetcivi.knightcli.constant.Constants;
import com.ummetcivi.knightcli.domain.GameContext;
import com.ummetcivi.knightcli.domain.GameMap;
import com.ummetcivi.knightcli.provider.StorageProvider;
import com.ummetcivi.knightcli.storage.ContextStorage;
import com.ummetcivi.knightcli.util.RandomNumberGenerator;

import java.util.function.BiConsumer;

public class GameContextImpl implements GameContext {

    private static final long serialVersionUID = 7767076392852455912L;

    private static GameContextImpl currentContext;
    Game game;
    private transient ContextStorage contextStorage;
    private transient RandomNumberGenerator randomNumberGenerator;
    private transient int delayInMilliseconds;
    private transient GameMap gameMap;

    private GameContextImpl() {
        contextStorage = StorageProvider.fileBasedGameStorage();
        randomNumberGenerator = RandomNumberGenerator.getInstance();
        delayInMilliseconds = Constants.DELAY;
        gameMap = new GameMapImpl();
        ((GameMapImpl) gameMap).fill(randomNumberGenerator::generateRandomBetween);
    }

    public static GameContextImpl currentContext() {
        if (currentContext == null) {
            currentContext = new GameContextImpl();
        }
        return currentContext;
    }

    public void createGame() {
        game = new Game();
    }

    public void loadGame() {
        currentContext = (GameContextImpl) contextStorage.load(Constants.SAVE_FILE_NAME);
    }

    public void saveGame() {
        contextStorage.save(this, Constants.SAVE_FILE_NAME);
    }

    @Override
    public void createRandomOpponent() {
        int characterIndex = randomNumberGenerator.generateRandomBetween(0, CharacterType.values().length);

        int level = randomNumberGenerator
                .generateRandomBetween(game.getPlayerLevel() - 5 <= 0 ? 1 : game.getPlayerLevel() - 5,
                        game.getPlayerLevel() + 5);

        this.game.opponent(CharacterType.values()[characterIndex], level);
    }

    @Override
    public void createCharacter(String characterId) {
        int characterIndex = Integer.valueOf(characterId) - 1;
        this.game.character(CharacterType.values()[characterIndex]);
    }

    @Override
    public FightStatus fight(BiConsumer<String, Object[]> printFunction) {
        boolean playersTurn = true;
        FightStatus fightStatus;

        while ((fightStatus = game.fight(playersTurn, printFunction, randomNumberGenerator::generateRandomBetween))
                == FightStatus.CONTINUE) {
            try {
                Thread.sleep(delayInMilliseconds);
            } catch (InterruptedException e) {
                //Ignore
            }

            playersTurn = !playersTurn;
        }

        return fightStatus;
    }

    @Override
    public void printPlayer(BiConsumer<String, Object[]> printFunction) {
        game.printPlayer(printFunction);
    }

    @Override
    public void printWithOpponent(BiConsumer<String, Object[]> printFunction) {
        game.printWithOpponent(printFunction);
    }

    @Override
    public void printMap(BiConsumer<String, Object[]> printFunction) {
        gameMap.print(printFunction);
    }

    @Override
    public boolean goTo(int x, int y) {
        int mobIndex = gameMap.pointOf(x, y);

        boolean mobFound = mobIndex >= 0;

        if (mobFound) {
            game.opponent(Mob.getByIndex(mobIndex));
        }

        gameMap.move(x, y);

        return mobFound;
    }
}
