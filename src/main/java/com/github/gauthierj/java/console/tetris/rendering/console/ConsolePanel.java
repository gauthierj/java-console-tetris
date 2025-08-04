package com.github.gauthierj.java.console.tetris.rendering.console;

import com.github.gauthierj.java.console.tetris.model.Position;

import static com.github.gauthierj.java.console.tetris.model.Position.at;
import static com.github.gauthierj.java.console.tetris.rendering.console.AnsiHelper.place;

public class ConsolePanel implements Panel {
    private static final String ANSI_CSI = "\033[";

    private final Position position;
    private final int columns;
    private final int rows;
    private final String[][] buffer;
    private final boolean withBorders;
    private final Character[] borderStyle = new Character[]{'╔', '╗', '╚', '╝', '═', '║'};

    public ConsolePanel(int rows,
                        int columns,
                        Position position,
                        boolean withBorders) {
        this.position = position;
        this.columns = columns;
        this.rows = rows;
        this.buffer = new String[rows][columns];
        this.withBorders = withBorders;
        clearBuffer();
    }

    @Override
    public void render() {
        if (withBorders) {
            for (int row = 0; row < rows + 1; row++) {
                place(at(row, 0).relativeTo(position));
                if (row == 0) {
                    System.out.print(borderStyle[0] + borderStyle[4].toString().repeat(columns) + borderStyle[1]);
                } else if (row < rows) {
                    System.out.print(borderStyle[5]);
                    for (int col = 0; col < columns; col++) {
                        System.out.print(buffer[row][col]);
                    }
                    System.out.print(borderStyle[5]);
                } else {
                    System.out.print(borderStyle[2] + borderStyle[4].toString().repeat(columns) + borderStyle[3]);
                }
            }
        } else {
            for (int row = 0; row < rows; row++) {
                place(at(row, 0)
                              .relativeTo(position));
                for (int col = 0; col < columns; col++) {
                    System.out.print(buffer[row][col]);
                }
            }
        }
    }

    public void write(int x,
                      int y,
                      String text) {
        if (x < 0 || x >= columns || y < 0 || y >= rows) return;

        for (int i = 0; i < text.length(); i++) {
            if (x + i < columns) {
                buffer[y][x + i] = text;
            } else {
                break; // Stop if text exceeds panel width
            }
        }
    }

    public void writeText(int x,
                      int y,
                      String text) {
        if (x < 0 || x >= columns || y < 0 || y >= rows) return;

        for (int i = 0; i < text.length(); i++) {
            if (x + i < columns) {
                buffer[y][x + i] = text.substring(i, i + 1);
            } else {
                break; // Stop if text exceeds panel width
            }
        }
    }

    public void clear() {
        clearBuffer();
        render();
    }

    private void clearBuffer() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                buffer[row][col] = " ";
            }
        }
    }
}
