package com.github.gauthierj.java.console.tetris.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

/**
 * Initial state :
 *
 * #000
 * 0#00
 * 00#0
 * 000#
 * 0000
 */
class NonEmptyCellArrayImplTest {

    @Test
    public void size() {
        int rows = 5;
        int columns = 4;

        CellArray cellArray = newCellArray();

        Assertions.assertEquals(rows, cellArray.rows());
        Assertions.assertEquals(columns, cellArray.columns());
    }

    @Test
    public void iterateByRowColumn() {
        int rows = 5;
        int columns = 4;

        CellArray cellArray = newCellArray();

        for (int row = 0; row < cellArray.rows(); row++) {
            for (int column = 0; column < cellArray.columns(); column++) {
                Assertions.assertEquals(column == row, cellArray.at(row, column).isOccupied());
            }
        }
    }

    @Test
    public void iterateByPosition() {
        int rows = 5;
        int columns = 4;

        CellArray cellArray = newCellArray();
        for (int row = 0; row < cellArray.rows(); row++) {
            for (int column = 0; column < cellArray.columns(); column++) {
                if(column == row) {
                    Assertions.assertTrue(cellArray.at(Position.at(row, column)).map(Cell::isOccupied).orElse(false));
                } else {
                    Assertions.assertTrue(cellArray.at(Position.at(row, column)).map(Cell::isFree).orElse(false));
                }
            }
        }
    }

    @Test
    public void inexistingPosition() {
        int rows = 5;
        int columns = 4;

        CellArray cellArray = newCellArray();
        Assertions.assertTrue(cellArray.at(Position.at(1, 15)).isEmpty());
        Assertions.assertTrue(cellArray.at(Position.at(1, -5)).isEmpty());
        Assertions.assertTrue(cellArray.at(Position.at(13, 1)).isEmpty());
        Assertions.assertTrue(cellArray.at(Position.at(-5, 1)).isEmpty());
    }

    @Test
    public void iterateByRows() {
        int rows = 5;
        int columns = 4;

        CellArray cellArray = newCellArray();

        for (int row = 0; row < rows; row++) {
            List<Cell> cells = cellArray.row(row);
            Assertions.assertEquals(columns, cells.size());
            for (int column = 0; column < cells.size(); column++) {
                Assertions.assertEquals(column == row, cells.get(column).isOccupied());
            }
        }
    }

    @Test
    public void iterateByColumns() {
        int rows = 5;
        int columns = 4;

        CellArray cellArray = newCellArray();

        for (int column = 0; column < columns; column++) {
            List<Cell> cells = cellArray.column(column);
            Assertions.assertEquals(rows, cells.size());
            for (int row = 0; row < cells.size(); row++) {
                Assertions.assertEquals(column == row, cells.get(row).isOccupied());
            }
        }
    }

    /* Insert
        ####
        ####
        at position 1,1
        =>
        #000
        O###
        0###
        000#
        0000
    */

    @Test
    public void insert_overFlowRight() {
        int rows = 5;
        int columns = 4;

        CellArray cellArray = newCellArray();

        CellArray toInsert = CellArray.of(Map.of(
                Position.at(0, 0), Cell.occupied("#"),
                Position.at(0, 1), Cell.occupied("#"),
                Position.at(0, 2), Cell.occupied("#"),
                Position.at(0, 3), Cell.occupied("#"),
                Position.at(1, 0), Cell.occupied("#"),
                Position.at(1, 1), Cell.occupied("#"),
                Position.at(1, 2), Cell.occupied("#"),
                Position.at(1, 3), Cell.occupied("#")
        ));

        CellArray newArray = cellArray.insert(toInsert, Position.at(1, 1));

        for (int row = 0; row < cellArray.rows(); row++) {
            for (int column = 0; column < cellArray.columns(); column++) {
                if(row > 0 && row <= 2 && column > 0 && column <= 3) {
                    Assertions.assertTrue(newArray.at(row, column).isOccupied());
                } else {
                    Assertions.assertEquals(row == column, newArray.at(row, column).isOccupied());
                }
            }
        }
    }

    /* Insert
        ####
        ####
        at position 1,-1
        =>
        #000
        ###0
        ###0
        000#
        0000
    */
    @Test
    public void insert_overFlowLeft() {
        int rows = 5;
        int columns = 4;

        CellArray cellArray = newCellArray();

        CellArray toInsert = CellArray.of(Map.of(
                Position.at(0, 0), Cell.occupied("#"),
                Position.at(0, 1), Cell.occupied("#"),
                Position.at(0, 2), Cell.occupied("#"),
                Position.at(0, 3), Cell.occupied("#"),
                Position.at(1, 0), Cell.occupied("#"),
                Position.at(1, 1), Cell.occupied("#"),
                Position.at(1, 2), Cell.occupied("#"),
                Position.at(1, 3), Cell.occupied("#")
        ));

        CellArray newArray = cellArray.insert(toInsert, Position.at(1, -1));

        for (int row = 0; row < cellArray.rows(); row++) {
            for (int column = 0; column < cellArray.columns(); column++) {
                if(row > 0 && row <= 2 && column <= 2) {
                    Assertions.assertTrue(newArray.at(row, column).isOccupied());
                } else {
                    Assertions.assertEquals(row == column, newArray.at(row, column).isOccupied());
                }
            }
        }
    }
    @Test
    public void clear() {
        int rows = 5;
        int columns = 4;

        CellArray cellArray = newCellArray();

        CellArray clear = cellArray.clear();

        Assertions.assertEquals(rows, clear.rows());
        Assertions.assertEquals(columns, clear.columns());

        for (int row = 0; row < clear.rows(); row++) {
            for (int column = 0; column < clear.columns(); column++) {
                Assertions.assertTrue(clear.at(row, column).isFree());
            }
        }
    }

    private static CellArray newCellArray() {
        return CellArray.of(Map.ofEntries(
                Map.entry(Position.at(0, 0), Cell.occupied()),
                Map.entry(Position.at(1, 1), Cell.occupied()),
                Map.entry(Position.at(2, 2), Cell.occupied()),
                Map.entry(Position.at(3, 3), Cell.occupied()),
                Map.entry(Position.at(4, 0), Cell.empty())
        ));
    }
}