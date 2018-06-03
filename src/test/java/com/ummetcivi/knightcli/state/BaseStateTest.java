package com.ummetcivi.knightcli.state;

import com.ummetcivi.knightcli.domain.GameContext;
import com.ummetcivi.knightcli.io.GameIO;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;

public abstract class BaseStateTest {

    @Mock
    protected StateContext stateContextMock;
    @Mock
    protected GameContext gameContextMock;
    @Mock
    protected GameIO gameIOMock;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
}
