package com.github.gauthierj.java.console.tetris.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TetrominoFactory {

    private final static Random random = new Random();

    public static Tetromino random() {
        int i = random.nextInt(7);
        return switch (i) {
            case 0 -> newI();
            case 1 -> newO();
            case 2 -> newT();
            case 3 -> newS();
            case 4 -> newZ();
            case 5 -> newJ();
            case 6 -> newL();
            default -> throw new IllegalStateException("Cannot happen");
        };
    }

    public static Tetromino newI() {
        return new TetrominoImpl(
                fromRepresentation("""
                                           ····
                                           ····
                                           IIII
                                           ····
                                           """));
    }

    public static Tetromino newO() {
        return new TetrominoImpl(
                fromRepresentation("""
                                           OO
                                           OO
                                           """));
    }

    public static Tetromino newT() {
        return new TetrominoImpl(
                fromRepresentation("""
                                           ···
                                           TTT
                                           ·T·
                                           """));
    }

    public static Tetromino newS() {
        return new TetrominoImpl(
                fromRepresentation("""
                                           ···
                                           ·SS
                                           SS·
                                           """));
    }

    public static Tetromino newZ() {
        return new TetrominoImpl(
                fromRepresentation("""
                                           ···
                                           ZZ·
                                           ·ZZ
                                           """));
    }

    public static Tetromino newJ() {
        return new TetrominoImpl(
                fromRepresentation("""
                                           ···
                                           JJJ
                                           ··J
                                           """));
    }

    public static Tetromino newL() {
        return new TetrominoImpl(
                fromRepresentation("""
                                           ···
                                           LLL
                                           L··
                                           """));
    }

    private static Map<Position, Cell> fromRepresentation(String representation) {
        Map<Position, Cell> cells = new HashMap<>();

        String[] lines = representation.split("\n");
        for (int row = 0; row < lines.length; row++) {
            String line = lines[row];
            for (int column = 0; column < line.length(); column++) {
                cells.put(
                        Position.at(row, column),
                        line.charAt(column) == '·' ?
                                Cell.empty() :
                                Cell.occupied(line.substring(column, column + 1)));
            }
        }
        return cells;
    }
}
