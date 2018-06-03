package com.ummetcivi.knightcli.state;

import com.ummetcivi.knightcli.domain.GameContext;
import com.ummetcivi.knightcli.io.GameIO;

public enum MenuStates implements State {
    MAIN_MENU {
        @Override
        public boolean execute(StateContext stateContext, GameContext gameContext, GameIO io) {
            io.clear();
            io.printLine("\n"
                    + " _   __        _         _      _     _____         _____                            _       \n"
                    + "| | / /       (_)       | |    | |   |  _  |       /  __ \\                          | |      \n"
                    + "| |/ /  _ __   _   __ _ | |__  | |_  | | | | _ __  | /  \\/  ___   _ __   ___   ___  | |  ___ \n"
                    + "|    \\ | '_ \\ | | / _` || '_ \\ | __| | | | || '_ \\ | |     / _ \\ | '_ \\ / __| / _ \\ | | / _ \\\n"
                    + "| |\\  \\| | | || || (_| || | | || |_  \\ \\_/ /| | | || \\__/\\| (_) || | | |\\__ \\| (_) || ||  __/\n"
                    + "\\_| \\_/|_| |_||_| \\__, ||_| |_| \\__|  \\___/ |_| |_| \\____/ \\___/ |_| |_||___/ \\___/ |_| \\___|\n"
                    + "                   __/ |                                                                     \n"
                    + "                  |___/                                                                      \n");
            io.printLine("Welcome! What would you like to do?\n");

            io.printLine("1. Create new game");
            io.printLine("2. Load game\n");

            io.printLine("You can always use 'save' command to save game and 'exit' command to leave.\n");

            stateContext.setPossibleCommands(
                    Command.of("1", NEW_GAME),
                    Command.of("2", LOAD),
                    Command.exitCommand());

            return true;
        }
    },
    NEW_GAME {
        @Override
        public boolean execute(StateContext stateContext, GameContext gameContext, GameIO io) {
            io.clear();
            gameContext.createGame();
            io.printLine("Please select character:");
            io.printLine("1. Warrior");
            io.printLine("2. Rogue");
            io.printLine("3. Mage");

            stateContext.setPossibleCommands(
                    Command.of("\\d", SELECT_CHARACTER),
                    Command.exitCommand());

            return true;
        }
    },
    EXIT {
        @Override
        public boolean execute(StateContext stateContext, GameContext gameContext, GameIO io) {
            io.printLine("Goodbye!");

            return false;
        }
    },
    SELECT_CHARACTER {
        @Override
        public boolean execute(StateContext stateContext, GameContext gameContext, GameIO io) {
            io.clear();
            gameContext.createCharacter(stateContext.getCurrentInput());
            gameContext.printPlayer(io::printWithFormat);
            stateContext.setNextState(GameStates.PLAY);

            return true;
        }
    },
    LOAD {
        @Override
        public boolean execute(StateContext stateContext, GameContext gameContext, GameIO io) {
            io.clear();
            gameContext.loadGame();
            io.printLine("Game loaded!");
            stateContext.setNextState(GameStates.PLAY);

            return true;
        }
    },
    SAVE {
        @Override
        public boolean execute(StateContext stateContext, GameContext gameContext, GameIO io) {
            io.clear();
            gameContext.saveGame();
            io.printLine("Game saved! Would you like to continue or exit?");
            io.printLine("1. Continue");
            io.printLine("2. Exit");

            stateContext.setPossibleCommands(
                    Command.of("1", GameStates.PLAY),
                    Command.of("2", EXIT));

            return true;
        }
    },
}
