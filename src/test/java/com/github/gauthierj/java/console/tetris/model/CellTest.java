package com.github.gauthierj.java.console.tetris.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    public void clear_occupied() {
        Cell occupied = Cell.occupied();
        Assertions.assertTrue(occupied.clear().isFree());
    }

    @Test
    public void clear_empty() {
        Cell empty = Cell.empty();
        Assertions.assertTrue(empty.clear().isFree());
    }

    @Test
    public void fill_occupied() {
        Cell occupied = Cell.occupied();
        Assertions.assertTrue(occupied.fill().isOccupied());
    }

    @Test
    public void fill_empty() {
        Cell empty = Cell.empty();
        Assertions.assertTrue(empty.fill().isOccupied());
    }
}