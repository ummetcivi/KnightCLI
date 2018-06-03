package com.ummetcivi.knightcli.state;

import java.util.Objects;

public class Command {

    private final String input;
    private final State state;

    private Command(String input, State state) {
        this.input = input;
        this.state = state;
    }

    static Command of(String input, State state) {
        return new Command(input, state);
    }

    static Command saveCommand() {
        return new Command("save", MenuStates.SAVE);
    }

    static Command exitCommand() {
        return new Command("exit", MenuStates.EXIT);
    }

    public String getInput() {
        return input;
    }

    public State getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Command command = (Command) o;
        return Objects.equals(input, command.input) &&
                Objects.equals(state, command.state);
    }

    @Override
    public int hashCode() {

        return Objects.hash(input, state);
    }
}
