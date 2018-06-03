package com.ummetcivi.knightcli.provider;

import com.ummetcivi.knightcli.storage.ContextStorage;
import com.ummetcivi.knightcli.storage.impl.FileBasedContextStorage;

public class StorageProvider {

    public static ContextStorage fileBasedGameStorage() {
        return FileBasedContextStorage.getInstance();
    }
}
