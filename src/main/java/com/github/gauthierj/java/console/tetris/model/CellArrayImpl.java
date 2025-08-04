package com.github.gauthierj.java.console.tetris.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.function.Function.identity;

public class CellArrayImpl implements CellArray {

    private final Cell[][] cells;

    public CellArrayImpl(Map<Position, Cell> cells) {
        this.cells = toArray(cells);
    }

    public CellArrayImpl(int rows,
                         int columns) {
        cells = new Cell[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                cells[row][column] = Cell.empty();
            }
        }
    }

    @Override
    public List<Cell> row(int row) {
        return List.of(cells[row]);
    }

    @Override
    public List<Cell> column(int column) {
        return IntStream.range(0, rows())
                .mapToObj(row -> cells[row][column])
                .toList();
    }

    @Override
    public Map<Position, Cell> cells() {
        return IntStream.range(0, rows()).boxed()
                .flatMap(row -> IntStream.range(0, columns())
                        .mapToObj(column -> Position.at(row, column)))
                .collect(Collectors.toMap(identity(), position -> cells[position.row()][position.column()]));
    }

    @Override
    public Optional<Cell> at(Position position) {
        return Optional.of(position)
                .filter(pos -> pos.row() >= 0)
                .filter(pos -> pos.row() < rows())
                .filter(pos -> pos.column() >= 0)
                .filter(pos -> pos.column() < columns())
                .map(pos -> cells[pos.row()][pos.column()]);
    }

    @Override
    public int rows() {
        return cells.length;
    }

    @Override
    public int columns() {
        return cells[0].length;
    }

    @Override
    public CellArray insert(CellArray cellArray,
                            Position position) {
        Map<Position, Cell> merge = new HashMap<>(this.cells());

        cellArray.cells().entrySet().stream()
                .filter(entry -> entry.getValue().isOccupied())
                .filter(entry -> entry.getKey().relativeTo(position).row() >= 0)
                .forEach(entry -> merge.put(entry.getKey().relativeTo(position), entry.getValue()));

        return new CellArrayImpl(merge);
    }

    @Override
    public CellArray clear() {
        return new CellArrayImpl(rows(), columns());
    }

    private Cell[][] toArray(Map<Position, Cell> cells) {
        int rows = cells.keySet().stream().mapToInt(Position::row).max().orElse(0) + 1;
        int columns = cells.keySet().stream().mapToInt(Position::column).max().orElse(0) + 1;
        return IntStream.range(0, rows)
                .mapToObj(row -> IntStream.range(0, columns)
                        .mapToObj(column -> cells.getOrDefault(Position.at(row, column), Cell.empty()))
                        .toArray(Cell[]::new))
                .toArray(Cell[][]::new);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellArrayImpl cellArray = (CellArrayImpl) o;
        return Objects.deepEquals(cells, cellArray.cells);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(cells);
    }
}
