package com.ummetcivi.knightcli;

import com.ummetcivi.knightcli.domain.GameContext;
import com.ummetcivi.knightcli.io.GameIO;
import com.ummetcivi.knightcli.provider.GameContextProvider;
import com.ummetcivi.knightcli.provider.GameIOProvider;
import com.ummetcivi.knightcli.state.Command;
import com.ummetcivi.knightcli.state.MenuStates;
import com.ummetcivi.knightcli.state.State;
import com.ummetcivi.knightcli.state.StateContext;

public class Main {

    public static void main(String[] args) {
        StateContext stateContext = StateContext.getInstance();
        GameIO gameIO = GameIOProvider.consoleIO();
        GameContext gameContext = GameContextProvider.currentContext();

        startProcess(stateContext, gameIO, gameContext);
    }

    private static void startProcess(StateContext stateContext, GameIO gameIO, GameContext gameContext) {
        stateContext.setNextState(MenuStates.MAIN_MENU);
        boolean shouldContinue = true;

        while (shouldContinue) {
            if (stateContext.getNextState() != null) {
                stateContext.setCurrentInput(null);
                stateContext.getNextState().execute(stateContext, gameContext, gameIO);
            } else {
                String input = gameIO.readLine();
                stateContext.setCurrentInput(input);
                State state = null;

                for (Command command : stateContext.getPossibleCommands()) {
                    if (input.equals(command.getInput()) || input.matches(command.getInput())) {
                        state = command.getState();
                        break;
                    }
                }

                if (state != null) {
                    shouldContinue = state.execute(stateContext, gameContext, gameIO);
                } else {
                    gameIO.printLine("Input is not valid! Try again.");
                }
            }
        }
    }
}
