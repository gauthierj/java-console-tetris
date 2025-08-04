package com.github.gauthierj.java.console.tetris;

import com.github.gauthierj.java.console.tetris.model.PlayField;
import com.github.gauthierj.java.console.tetris.model.Position;
import com.github.gauthierj.java.console.tetris.model.Tetromino;

import static com.github.gauthierj.java.console.tetris.model.Position.at;

public class PositionHelper {

    public static Position topPosition(Tetromino tetromino, PlayField playField) {
        return at(-tetromino.firstNonEmptyRow(), (int) ((double) (playField.columns() - tetromino.columns()) / 2));
    }
}
