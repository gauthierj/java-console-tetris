package com.github.gauthierj.java.console.tetris.util;

public abstract class InitializableAndDestroyable implements Initiallizable, Destroyable, _Initializable {

    public final void init() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::destroy));
        this.doInit();
    }
}
