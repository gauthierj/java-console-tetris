package com.github.gauthierj.java.console.tetris.rendering.console;

public class TetrominoTypeRenderer {

    public static String render(String type) {
        return switch (type) {
            case "I" -> AnsiHelper.red("█");
            case "J" -> AnsiHelper.purple("█");
            case "L" -> AnsiHelper.brightBlue("█");
            case "O" -> AnsiHelper.cyan("█");
            case "S" -> AnsiHelper.yellow("█");
            case "T" -> AnsiHelper.green("█");
            case "Z" -> AnsiHelper.blue("█");
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
