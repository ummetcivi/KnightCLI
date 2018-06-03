package com.ummetcivi.knightcli.state;

public class StateContext {

    private static final StateContext INSTANCE = new StateContext();

    private Command[] possibleCommands;
    private State nextState;
    private String currentInput;

    private StateContext() {
    }

    public static StateContext getInstance() {
        return INSTANCE;
    }

    public Command[] getPossibleCommands() {
        return possibleCommands;
    }

    void setPossibleCommands(Command... possibleCommands) {
        this.possibleCommands = possibleCommands;
        this.nextState = null;
    }

    public State getNextState() {
        return nextState;
    }

    public void setNextState(State state) {
        this.nextState = state;
        this.possibleCommands = null;
    }

    String getCurrentInput() {
        return currentInput;
    }

    public void setCurrentInput(String currentInput) {
        this.currentInput = currentInput;
    }
}
