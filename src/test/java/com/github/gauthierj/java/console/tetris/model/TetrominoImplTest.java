package com.github.gauthierj.java.console.tetris.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

class TetrominoImplTest {

    /**
     * 000    0X0
     * XXX => XX0
     * 0x0    0X0
     */
    @Test
    public void rotateRight() {

        TetrominoImpl tetromino = new TetrominoImpl(Map.of(Position.at(1, 0), Cell.occupied(),
                                                           Position.at(1, 1), Cell.occupied(),
                                                           Position.at(1, 2), Cell.occupied(),
                                                           Position.at(2, 1), Cell.occupied()));

        Assertions.assertEquals(new TetrominoImpl(Map.of(Position.at(0, 1), Cell.occupied(),
                                                         Position.at(1, 0), Cell.occupied(),
                                                         Position.at(1, 1), Cell.occupied(),
                                                         Position.at(2, 1), Cell.occupied(),
                                                         Position.at(2, 2), Cell.empty())), tetromino.rotateRight());
    }

    /**
     * 000    0X0
     * XXX => 0XX
     * 0x0    0X0
     */
    @Test
    public void rotateLeft() {

        TetrominoImpl tetromino = new TetrominoImpl(Map.of(Position.at(1, 0), Cell.occupied(),
                                                           Position.at(1, 1), Cell.occupied(),
                                                           Position.at(1, 2), Cell.occupied(),
                                                           Position.at(2, 1), Cell.occupied()));

        Assertions.assertEquals(new TetrominoImpl(Map.of(Position.at(0, 1), Cell.occupied(),
                                                         Position.at(1, 1), Cell.occupied(),
                                                         Position.at(1, 2), Cell.occupied(),
                                                         Position.at(2, 1), Cell.occupied())), tetromino.rotateLeft());
    }

    @Test
    public void firstNonEmptyRow() {
        Tetromino tetromino = new TetrominoImpl(Map.of(Position.at(1, 0), Cell.occupied(),
                                                           Position.at(1, 1), Cell.occupied(),
                                                           Position.at(1, 2), Cell.occupied(),
                                                           Position.at(2, 1), Cell.occupied()));

        Assertions.assertEquals(1, tetromino.firstNonEmptyRow());

        tetromino = new TetrominoImpl(Map.of(Position.at(0, 1), Cell.occupied(),
                                             Position.at(1, 1), Cell.occupied(),
                                             Position.at(1, 2), Cell.occupied(),
                                             Position.at(2, 1), Cell.occupied()));

        Assertions.assertEquals(0, tetromino.firstNonEmptyRow());
    }
}