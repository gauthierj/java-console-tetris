package com.github.gauthierj.java.console.tetris.model;

import java.util.List;

public interface PlayField extends CellArray {

    boolean canInsert(CellArray cellArray,
                      Position position);

    PlayField insert(CellArray cellArray,
                     Position position);

    default PlayField wipeRows(List<Integer> rows) {
        return rows.stream()
                .reduce(this,
                        PlayField::wipeRow,
                        (playField1, playField2) -> playField1); // Useless argument for non parallel stream
    }

    PlayField wipeRow(int row);

    List<Integer> getFullRows();

    static PlayField ofSize(int rows,
                            int columns) {
        return new PlayFieldImpl(rows, columns);
    }

    Position topPosition(Tetromino tetromino);
}
