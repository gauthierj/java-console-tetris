package com.github.gauthierj.java.console.tetris.rendering.console;

import com.github.gauthierj.java.console.tetris.TetrisStateHolder;
import com.github.gauthierj.java.console.tetris.model.Cell;
import com.github.gauthierj.java.console.tetris.model.CellArray;
import com.github.gauthierj.java.console.tetris.model.Position;

public class PlayFieldPanel implements Panel {

    private final TetrisStateHolder stateHolder;
    private final ConsolePanel consolePanel;
    private final int width;

    public PlayFieldPanel(TetrisStateHolder stateHolder, Position position, int width) {
        this.stateHolder = stateHolder;
        CellArray array = stateHolder.getState().cellArray();
        this.consolePanel = new ConsolePanel(array.rows(), array.columns() * width, position, false);
        this.width = width;
    }

    @Override
    public void render() {
        if(!stateHolder.getState().isPaused()) {
            CellArray array = stateHolder.getState().cellArray();
            for (int row = 0; row < array.rows(); row++) {
                for (int column = 0; column < array.columns(); column++) {
                    Cell at = array.at(row, column);
                    for (int i = 0; i < width; i++) {
                        consolePanel.write(column * width + i, row, at.isOccupied() ? TetrominoTypeRenderer.render(at.type()) : new String(new char[] { /*183*/ ' ' }));
                    }
                }
            }
            consolePanel.render();
        }
    }
}
