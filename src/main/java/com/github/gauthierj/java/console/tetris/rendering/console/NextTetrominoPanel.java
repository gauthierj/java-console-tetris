package com.github.gauthierj.java.console.tetris.rendering.console;

import com.github.gauthierj.java.console.tetris.TetrisStateHolder;
import com.github.gauthierj.java.console.tetris.model.*;

import java.util.stream.IntStream;

public class NextTetrominoPanel implements Panel {

    public static final int rows = 3;
    public static final int columns = 12;
    private final TetrisStateHolder stateHolder;
    private final ConsolePanel consolePanel;
    private CellArray cells;

    public NextTetrominoPanel(TetrisStateHolder stateHolder,
                              Position position) {
        this.stateHolder = stateHolder;
        this.consolePanel = new ConsolePanel(rows, columns, position, true);
        this.cells = new CellArrayImpl(rows, columns);
    }

    @Override
    public void render() {
        Tetromino nextTetromino = stateHolder.getState().nextTetromino();
        cells = cells.clear()
                .insert(nextTetromino,
                        Position.at(
                                1 - nextTetromino.firstNonEmptyRow(),
                                (columns - nextTetromino.columns()) / 2));

        IntStream.range(0, cells.rows())
                .forEach(row -> IntStream.range(0, cells.columns())
                        .forEach(column -> consolePanel.write(column, row, render(cells.at(row, column)))));

        consolePanel.render();
    }

    private String render(Cell cell) {
        if (cell.isOccupied()) {
            return TetrominoTypeRenderer.render(cell.type());
        }
        return " ";
    }
}
