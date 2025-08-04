package com.github.gauthierj.java.console.tetris.rendering.console;

import com.github.gauthierj.java.console.tetris.model.Position;
import com.github.gauthierj.java.console.tetris.util.InitializableAndDestroyable;

import java.util.ArrayList;
import java.util.List;

import static com.github.gauthierj.java.console.tetris.rendering.console.AnsiHelper.*;

public class ConsoleScreen extends InitializableAndDestroyable {

    private final ConsolePanel mainPanel;
    private final List<Panel> panels = new ArrayList<>();

    public ConsoleScreen(int rows, int columns, boolean withBorders) {
        this.mainPanel = new ConsolePanel(rows, columns, Position.at(1, 1), withBorders);
    }

    public void doInit() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::destroy));
        this.hideCursor();
        this.clear();
        mainPanel.render();
    }

    public void destroy() {
        this.showCursor();
        this.clear();
    }

    public void render() {
        panels.forEach(Panel::render);
    }

    public ConsoleScreen addPanel(Panel panel) {
        panels.add(panel);
        return this;
    }

    private void hideCursor() {
        System.out.print(HIDE_CURSOR);
        System.out.flush();
    }

    private void showCursor() {
        System.out.print(SHOW_CURSOR);
        System.out.flush();
    }

    private void clear() {
        System.out.println(CLEAR_SCREEN);
        System.out.flush();
    }
}
