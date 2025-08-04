package com.github.gauthierj.java.console.tetris.music;

import java.io.Closeable;

public interface Music extends Closeable {

    void init();

    void play();

    void pause();

    boolean isPlaying();
}
