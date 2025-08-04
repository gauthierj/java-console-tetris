package com.github.gauthierj.java.console.tetris.rendering.console;

import com.github.gauthierj.java.console.tetris.TetrisStateHolder;
import com.github.gauthierj.java.console.tetris.model.Position;

public class ScorePanel implements Panel {

    public static final int rows = 3;
    public static final int columns = 12;
    private final TetrisStateHolder stateHolder;
    private final ConsolePanel consolePanel;


    public ScorePanel(TetrisStateHolder stateHolder,
                      Position position) {
        this.stateHolder = stateHolder;
        this.consolePanel = new ConsolePanel(rows, columns, position, true);
    }

    @Override
    public void render() {
        this.consolePanel.writeText(1, 1, Integer.toString(stateHolder.getState().score()));
        this.consolePanel.render();
    }
}
