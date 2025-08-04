package com.github.gauthierj.java.console.tetris.model;

import com.github.gauthierj.java.console.tetris.util.ImmutableStyle;
import org.immutables.value.Value;

@ImmutableStyle
@Value.Immutable
public interface Cell {

    @Value.Parameter(order = 0)
    boolean isOccupied();
    @Value.Parameter(order = 1)
    String type();

    default boolean isFree() {
        return !isOccupied();
    }

    static Cell empty() {
        return empty("·");
    }

    static Cell empty(String type) {
        return CellImpl.of(false, type);
    }

    static Cell occupied() {
        return occupied("X");
    }

    static Cell occupied(String type) {
        return CellImpl.of(true, type);
    }


    default Cell clear() {
        return CellImpl.of(false, this.type());
    }

    default Cell fill() {
        return CellImpl.of(true, this.type());
    }
}
