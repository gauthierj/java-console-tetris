package com.github.gauthierj.java.console.tetris;

import com.github.gauthierj.java.console.tetris.model.CellArray;
import com.github.gauthierj.java.console.tetris.model.PlayField;
import com.github.gauthierj.java.console.tetris.model.Position;
import com.github.gauthierj.java.console.tetris.model.Tetromino;
import com.github.gauthierj.java.console.tetris.util.ImmutableStyle;
import org.immutables.value.Value;

@Value.Immutable
@ImmutableStyle
public interface TetrisState {

    @Value.Parameter(order = 0)
    PlayField playField();

    @Value.Parameter(order = 0)
    Position currentPosition();

    @Value.Parameter(order = 0)
    Tetromino currentTetromino();

    @Value.Parameter(order = 0)
    Tetromino nextTetromino();

    @Value.Default
    default boolean needToInsert() {
        return false;
    }

    @Value.Default
    default int level() {
        return 0;
    }

    @Value.Default
    default int lines() {
        return 0;
    }

    @Value.Default
    default int score() {
        return 0;
    }

    @Value.Default
    default int softDropCount() {
        return 0;
    }

    @Value.Default
    default boolean isPaused() {
        return false;
    }

    default CellArray cellArray() {
        return this.playField().insert(this.currentTetromino(), this.currentPosition());
    }

    default boolean isGameOver() {
        return this.needToInsert()
                && !this.playField().canInsert(
                this.nextTetromino(),
                this.playField().topPosition(this.nextTetromino()));
    }
}
