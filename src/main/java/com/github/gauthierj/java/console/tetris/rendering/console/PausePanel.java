package com.github.gauthierj.java.console.tetris.rendering.console;

import com.github.gauthierj.java.console.tetris.TetrisStateHolder;
import com.github.gauthierj.java.console.tetris.model.Position;

public class PausePanel implements Panel {

    public static final int rows = 6;
    public static final int columns = 18;
    private final TetrisStateHolder stateHolder;
    private final ConsolePanel consolePanel;


    public PausePanel(TetrisStateHolder stateHolder,
                      Position position) {
        this.stateHolder = stateHolder;
        this.consolePanel = new ConsolePanel(rows, columns, position, true);
        this.consolePanel.writeText(1, 2, " --- Paused ---");
        this.consolePanel.writeText(1, 3, "- 'r' to resume");
        this.consolePanel.writeText(1, 4, "- 'q' to quit");
    }

    @Override
    public void render() {
        if( stateHolder.getState().isPaused()) {
            this.consolePanel.render();
        }
    }
}
