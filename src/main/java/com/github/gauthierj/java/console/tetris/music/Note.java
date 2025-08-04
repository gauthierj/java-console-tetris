package com.github.gauthierj.java.console.tetris.music;

import com.github.gauthierj.java.console.tetris.util.ImmutableStyle;
import org.immutables.value.Value;

@Value.Immutable
@ImmutableStyle
public interface Note {

    @Value.Parameter
    int note();

    @Value.Parameter
    int octave();

    @Value.Parameter
    int duration();

}
