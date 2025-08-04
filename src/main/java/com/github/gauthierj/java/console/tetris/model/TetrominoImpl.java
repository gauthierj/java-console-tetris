package com.github.gauthierj.java.console.tetris.model;

import java.util.HashMap;
import java.util.Map;

public class TetrominoImpl extends CellArrayImpl implements Tetromino {

    private final int firstNonEmptyRow;

    public TetrominoImpl(Map<Position, Cell> cells) {
        super(cells);
        this.firstNonEmptyRow = this.cells().entrySet().stream()
                .filter(cellEntry -> cellEntry.getValue().isOccupied())
                .map(cellEntry -> cellEntry.getKey().row())
                .min(Integer::compareTo)
                .orElseThrow();
    }

    @Override
    public Tetromino rotateRight() {
        Map<Position, Cell> cells = new HashMap<>();
        for (int row = 0; row < this.rows(); row++) {
            for (int column = 0; column < this.columns(); column++) {
                cells.put(
                        Position.at(row, column),
                        this.at(this.columns() - column - 1, row));
            }
        }
        return new TetrominoImpl(cells);
    }

    @Override
    public Tetromino rotateLeft() {
        Map<Position, Cell> cells = new HashMap<>();
        for (int row = 0; row < this.rows(); row++) {
            for (int column = 0; column < this.columns(); column++) {
                cells.put(
                        Position.at(row, column),
                        this.at(column, this.rows() - row - 1));
            }
        }
        return new TetrominoImpl(cells);
    }

    @Override
    public int firstNonEmptyRow() {
        return firstNonEmptyRow;
    }
}
