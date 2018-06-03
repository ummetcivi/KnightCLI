package com.ummetcivi.knightcli.state;

import com.ummetcivi.knightcli.domain.GameContext;
import com.ummetcivi.knightcli.domain.impl.FightStatus;
import com.ummetcivi.knightcli.io.GameIO;

public enum GameStates implements State {

    PLAY {
        @Override
        public boolean execute(StateContext stateContext, GameContext gameContext, GameIO io) {
            io.clear();
            io.printLine("You can save with 'save' command. And use 'exit' whenever you want to go");
            io.printLine("Would you like to free farm and explore the map or battle?");

            io.printLine("1. Free farm");
            io.printLine("2. Battle!");

            stateContext.setPossibleCommands(
                    Command.saveCommand(),
                    Command.exitCommand(),
                    Command.of("1", FREE_FARM),
                    Command.of("2", BATTLE));

            return true;
        }
    },
    BATTLE {
        @Override
        public boolean execute(StateContext stateContext, GameContext gameContext, GameIO io) {
            io.clear();
            gameContext.createRandomOpponent();

            io.printLine("Just found an opponent. What do yo wanna do?");

            gameContext.printWithOpponent(io::printWithFormat);

            io.printLine("1. Fight");
            io.printLine("2. Keep searching");
            io.printLine("3. Go back");

            stateContext.setPossibleCommands(
                    Command.saveCommand(),
                    Command.exitCommand(),
                    Command.of("1", FIGHT),
                    Command.of("2", BATTLE),
                    Command.of("3", PLAY));

            return true;
        }
    },
    FREE_FARM {
        @Override
        public boolean execute(StateContext stateContext, GameContext gameContext, GameIO io) {
            io.clear();
            String input = stateContext.getCurrentInput();

            if (input.contains(",")) {
                String[] coordinates = input.split(",");
                int x = Integer.valueOf(coordinates[0].trim());
                int y = Integer.valueOf(coordinates[1].trim());

                try {
                    if (gameContext.goTo(x, y)) {
                        io.printLine("Just found a mob. What do you wanna do?");

                        gameContext.printWithOpponent(io::printWithFormat);

                        io.printLine("1. Fight");
                        io.printLine("2. Keep exploring");
                        io.printLine("3. Go back");

                        stateContext.setPossibleCommands(
                                Command.saveCommand(),
                                Command.exitCommand(),
                                Command.of("1", FIGHT),
                                Command.of("2", FREE_FARM),
                                Command.of("3", PLAY));
                    } else {
                        printIntro(stateContext, gameContext, io);
                    }
                } catch (IllegalArgumentException e) {
                    io.printLine(e.getMessage());
                    printAndSetCommand(stateContext, gameContext, io);
                }
            } else {
                printIntro(stateContext, gameContext, io);

            }

            return true;
        }

        private void printIntro(StateContext stateContext, GameContext gameContext, GameIO io) {
            io.printLine("Free farming now. You can explore the map");
            io.printLine("Please input coordinates in x,y format");
            printAndSetCommand(stateContext, gameContext, io);

        }

        private void printAndSetCommand(StateContext stateContext, GameContext gameContext, GameIO io) {
            gameContext.printMap(io::printWithFormat);
            stateContext.setPossibleCommands(
                    Command.saveCommand(),
                    Command.exitCommand(),
                    Command.of("\\d+,\\d+", FREE_FARM));
        }
    },
    FIGHT {
        @Override
        public boolean execute(StateContext stateContext, GameContext gameContext, GameIO io) {
            io.clear();
            FightStatus fightStatus = gameContext.fight(io::printWithFormat);

            io.printWithFormat("You've %s the fight\n", fightStatus.toString());
            io.printLine("Your stats look like this after fight:");
            gameContext.printPlayer(io::printWithFormat);
            io.printLine("\nPress ENTER to continue");

            stateContext.setPossibleCommands(
                    Command.of(".*", PLAY));

            return true;
        }
    }
}
