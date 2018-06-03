package com.ummetcivi.knightcli.state;

import com.ummetcivi.knightcli.domain.GameContext;
import com.ummetcivi.knightcli.io.GameIO;

public interface State {

    boolean execute(StateContext stateContext, GameContext gc, GameIO gameIO);
}
