package com.github.gauthierj.java.console.tetris.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CellArray {

    static CellArray of(Map<Position, Cell> cells) {
        return new CellArrayImpl(cells);
    }

    static CellArray empty(int rows,
                           int columns) {
        return new CellArrayImpl(rows, columns);
    }

    List<Cell> row(int row);

    List<Cell> column(int column);

    Map<Position, Cell> cells();

    Optional<Cell> at(Position position);

    default Cell at(int row,
                    int column) {
        return at(Position.at(row, column)).orElse(Cell.empty());
    }

    int rows();

    int columns();

    CellArray insert(CellArray cellArray,
                     Position position);

    CellArray clear();
}
