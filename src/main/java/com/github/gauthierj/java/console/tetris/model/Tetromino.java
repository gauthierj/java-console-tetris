package com.github.gauthierj.java.console.tetris.model;

public interface Tetromino extends CellArray {

    Tetromino rotateRight();

    Tetromino rotateLeft();

    int firstNonEmptyRow();
}
