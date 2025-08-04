package com.github.gauthierj.java.console.tetris.rendering.console;

import com.github.gauthierj.java.console.tetris.TetrisStateHolder;
import com.github.gauthierj.java.console.tetris.model.Position;

public class LinesPanel implements Panel {

    public static final int rows = 3;
    public static final int columns = 12;
    private final TetrisStateHolder tetrisStateHolder;
    private final ConsolePanel consolePanel;


    public LinesPanel(TetrisStateHolder tetrisStateHolder,
                      Position position) {
        this.tetrisStateHolder = tetrisStateHolder;
        this.consolePanel = new ConsolePanel(rows, columns, position, true);
    }

    @Override
    public void render() {
        this.consolePanel.writeText(1, 1, Integer.toString(tetrisStateHolder.getState().lines()));
        this.consolePanel.render();
    }
}
