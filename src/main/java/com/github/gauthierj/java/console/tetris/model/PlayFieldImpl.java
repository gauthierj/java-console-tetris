package com.github.gauthierj.java.console.tetris.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class PlayFieldImpl extends CellArrayImpl implements PlayField {

    public PlayFieldImpl(Map<Position, Cell> cells) {
        super(cells);
    }

    public PlayFieldImpl(int rows, int columns) {
        super(rows, columns);
    }

    @Override
    public boolean canInsert(CellArray cellArray,
                             Position position) {
        return cellArray.cells().entrySet().stream()
                .filter(entry -> entry.getValue().isOccupied())
                .filter(entry -> entry.getKey().relativeTo(position).row() >= 0)
                .allMatch(entry -> this.at(entry.getKey().relativeTo(position))
                        .map(Cell::isFree)
                        .orElse(false));
    }

    @Override
    public PlayField insert(CellArray cellArray,
                            Position position) {
        return new PlayFieldImpl(super.insert(cellArray, position).cells());
    }

    @Override
    public List<Integer> getFullRows() {
        return IntStream.range(0, this.rows())
                .filter(row -> this.row(row).stream().allMatch(Cell::isOccupied))
                .boxed()
                .toList();
    }

    @Override
    public PlayField wipeRow(int row) {
        Map<Position, Cell> merge = new HashMap<>();

        this.cells().forEach((position, cell) -> {
            if (position.row() == 0) {
                merge.put(position, Cell.empty());
            }
            if (position.row() < row) {
                merge.put(position.down(), cell);
            } else if (position.row() > row) {
                merge.put(position, cell);
            }
        });

        return new PlayFieldImpl(merge);
    }

    @Override
    public PlayField clear() {
        return new PlayFieldImpl(rows(), columns());
    }

    @Override
    public Position topPosition(Tetromino tetromino) {
        return Position.at(-tetromino.firstNonEmptyRow(), (int) ((double) (this.columns() - tetromino.columns()) / 2));
    }
}
