package com.github.gauthierj.java.console.tetris.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void testOrigin() {
        Position origin = Position.origin();
        assertEquals(0, origin.row());
        assertEquals(0, origin.column());
    }

    @Test
    void testAt() {
        Position position = Position.at(3, 5);
        assertEquals(3, position.row());
        assertEquals(5, position.column());
    }

    @Test
    void testRelativeTo() {
        int baseRow = 2;
        int baseCol = 3;
        int offestRow = 1;
        int offsetCol = -1;

        Position base = Position.at(baseRow, baseCol);
        Position offset = Position.at(offestRow, offsetCol);
        Position result = base.relativeTo(offset);

        assertEquals(baseRow + offestRow, result.row());
        assertEquals(baseCol + offsetCol, result.column());
    }

    @Test
    void testDownByOne() {
        Position position = Position.at(2, 3).down();
        assertEquals(3, position.row());
        assertEquals(3, position.column());
    }

    @Test
    void testDownByMultiple() {
        Position position = Position.at(2, 3).down(3);
        assertEquals(5, position.row());
        assertEquals(3, position.column());
    }

    @Test
    void testLeftByOne() {
        Position position = Position.at(2, 3).left();
        assertEquals(2, position.row());
        assertEquals(2, position.column());
    }

    @Test
    void testLeftByMultiple() {
        Position position = Position.at(2, 3).left(2);
        assertEquals(2, position.row());
        assertEquals(1, position.column());
    }

    @Test
    void testRightByOne() {
        Position position = Position.at(2, 3).right();
        assertEquals(2, position.row());
        assertEquals(4, position.column());
    }

    @Test
    void testRightByMultiple() {
        Position position = Position.at(2, 3).right(3);
        assertEquals(2, position.row());
        assertEquals(6, position.column());
    }
}