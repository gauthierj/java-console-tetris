package com.github.gauthierj.java.console.tetris.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class PlayFieldImplTest {

    @Test
    void canInsert() {
        PlayField playField = newPlayField();
        /**
         * ##
         * ##
         */
        CellArray cellArray = new CellArrayImpl(Map.of(
                Position.at(0, 0), Cell.occupied(),
                Position.at(0, 1), Cell.occupied(),
                Position.at(1, 0), Cell.occupied(),
                Position.at(1, 1), Cell.occupied()));

        Assertions.assertTrue(playField.canInsert(cellArray, Position.at(4, 0)));
        Assertions.assertTrue(playField.canInsert(cellArray, Position.at(4, 1)));
        Assertions.assertFalse(playField.canInsert(cellArray, Position.at(4, 2)));
        Assertions.assertFalse(playField.canInsert(cellArray, Position.at(5, 0)));
    }

    @Test
    void insert() {
        PlayField playField = newPlayField();
        /**
         * ##
         * ##
         */
        CellArray cellArray = new CellArrayImpl(Map.of(
                Position.at(0, 0), Cell.occupied(),
                Position.at(0, 1), Cell.occupied(),
                Position.at(1, 0), Cell.occupied(),
                Position.at(1, 1), Cell.occupied()));

        PlayFieldImpl expected = new PlayFieldImpl(
                Map.ofEntries(Map.entry(Position.at(2, 3), Cell.occupied()),
                              Map.entry(Position.at(2, 4), Cell.occupied()),
                              Map.entry(Position.at(3, 3), Cell.occupied()),
                              Map.entry(Position.at(3, 4), Cell.occupied()),
                              Map.entry(Position.at(5, 3), Cell.occupied()),
                              Map.entry(Position.at(6, 0), Cell.occupied()),
                              Map.entry(Position.at(6, 1), Cell.occupied()),
                              Map.entry(Position.at(6, 2), Cell.occupied()),
                              Map.entry(Position.at(6, 3), Cell.occupied()),
                              Map.entry(Position.at(6, 4), Cell.occupied()),
                              Map.entry(Position.at(7, 2), Cell.occupied()),
                              Map.entry(Position.at(7, 4), Cell.occupied()),
                              Map.entry(Position.at(8, 0), Cell.occupied()),
                              Map.entry(Position.at(8, 1), Cell.occupied()),
                              Map.entry(Position.at(8, 2), Cell.occupied()),
                              Map.entry(Position.at(8, 3), Cell.occupied()),
                              Map.entry(Position.at(8, 4), Cell.occupied()),
                              Map.entry(Position.at(9, 0), Cell.occupied()),
                              Map.entry(Position.at(9, 1), Cell.occupied()),
                              Map.entry(Position.at(9, 4), Cell.occupied())));

        Assertions.assertEquals(expected, playField.insert(cellArray, Position.at(2, 3)));
    }

    @Test
    void partialInsert() {
        PlayField playField = newPlayField();
        /**
         * ##
         * ##
         */
        CellArray cellArray = new CellArrayImpl(Map.of(
                Position.at(0, 0), Cell.occupied(),
                Position.at(0, 1), Cell.occupied(),
                Position.at(1, 0), Cell.occupied(),
                Position.at(1, 1), Cell.occupied()));

        PlayFieldImpl expected = new PlayFieldImpl(
                Map.ofEntries(Map.entry(Position.at(0, 3), Cell.occupied()),
                              Map.entry(Position.at(0, 4), Cell.occupied()),
                              Map.entry(Position.at(5, 3), Cell.occupied()),
                              Map.entry(Position.at(6, 0), Cell.occupied()),
                              Map.entry(Position.at(6, 1), Cell.occupied()),
                              Map.entry(Position.at(6, 2), Cell.occupied()),
                              Map.entry(Position.at(6, 3), Cell.occupied()),
                              Map.entry(Position.at(6, 4), Cell.occupied()),
                              Map.entry(Position.at(7, 2), Cell.occupied()),
                              Map.entry(Position.at(7, 4), Cell.occupied()),
                              Map.entry(Position.at(8, 0), Cell.occupied()),
                              Map.entry(Position.at(8, 1), Cell.occupied()),
                              Map.entry(Position.at(8, 2), Cell.occupied()),
                              Map.entry(Position.at(8, 3), Cell.occupied()),
                              Map.entry(Position.at(8, 4), Cell.occupied()),
                              Map.entry(Position.at(9, 0), Cell.occupied()),
                              Map.entry(Position.at(9, 1), Cell.occupied()),
                              Map.entry(Position.at(9, 4), Cell.occupied())));

        Assertions.assertEquals(expected, playField.insert(cellArray, Position.at(-1, 3)));
    }

    @Test
    void getFullRows() {
        PlayField playField = newPlayField();
        Assertions.assertEquals(List.of(6, 8), playField.getFullRows());
    }

    @Test
    void wipeRow() {
        PlayFieldImpl expected = new PlayFieldImpl(
                Map.ofEntries(Map.entry(Position.at(6, 3), Cell.occupied()),
                              Map.entry(Position.at(7, 0), Cell.occupied()),
                              Map.entry(Position.at(7, 1), Cell.occupied()),
                              Map.entry(Position.at(7, 2), Cell.occupied()),
                              Map.entry(Position.at(7, 3), Cell.occupied()),
                              Map.entry(Position.at(7, 4), Cell.occupied()),
                              Map.entry(Position.at(8, 0), Cell.occupied()),
                              Map.entry(Position.at(8, 1), Cell.occupied()),
                              Map.entry(Position.at(8, 2), Cell.occupied()),
                              Map.entry(Position.at(8, 3), Cell.occupied()),
                              Map.entry(Position.at(8, 4), Cell.occupied()),
                              Map.entry(Position.at(9, 0), Cell.occupied()),
                              Map.entry(Position.at(9, 1), Cell.occupied()),
                              Map.entry(Position.at(9, 4), Cell.occupied())));

        PlayField playField = newPlayField();

        Assertions.assertEquals(expected, playField.wipeRow(7));
    }

    @Test
    void clear() {
        PlayField playField = newPlayField();
        Assertions.assertEquals(new PlayFieldImpl(playField.rows(), playField.columns()), playField.clear());
    }

    /**
     * Return
     * <p>
     * 00000
     * 00000
     * 00000
     * 00000
     * 00000
     * 000#0
     * #####
     * 00#0#
     * #####
     * ##00#
     */
    private static PlayField newPlayField() {
        return new PlayFieldImpl(
                Map.ofEntries(Map.entry(Position.at(5, 3), Cell.occupied()),
                              Map.entry(Position.at(6, 0), Cell.occupied()),
                              Map.entry(Position.at(6, 1), Cell.occupied()),
                              Map.entry(Position.at(6, 2), Cell.occupied()),
                              Map.entry(Position.at(6, 3), Cell.occupied()),
                              Map.entry(Position.at(6, 4), Cell.occupied()),
                              Map.entry(Position.at(7, 2), Cell.occupied()),
                              Map.entry(Position.at(7, 4), Cell.occupied()),
                              Map.entry(Position.at(8, 0), Cell.occupied()),
                              Map.entry(Position.at(8, 1), Cell.occupied()),
                              Map.entry(Position.at(8, 2), Cell.occupied()),
                              Map.entry(Position.at(8, 3), Cell.occupied()),
                              Map.entry(Position.at(8, 4), Cell.occupied()),
                              Map.entry(Position.at(9, 0), Cell.occupied()),
                              Map.entry(Position.at(9, 1), Cell.occupied()),
                              Map.entry(Position.at(9, 4), Cell.occupied())));
    }
}