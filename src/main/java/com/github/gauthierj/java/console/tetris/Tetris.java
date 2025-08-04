package com.github.gauthierj.java.console.tetris;

import com.github.gauthierj.java.console.tetris.model.Position;
import com.github.gauthierj.java.console.tetris.model.TetrominoFactory;
import com.github.gauthierj.java.console.tetris.music.Korobeiniki;
import com.github.gauthierj.java.console.tetris.music.Music;
import com.github.gauthierj.java.console.tetris.rendering.console.*;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Tetris {

    private final int fps = 60;

    private final ConsoleScreen screen;
    private final UserInputReader userInputReader = new UserInputReader();
    private TetrisController controller;
    private Music music;

    
    private int level = 0;
    private long frames = 0;
    private int downFrames = 48;

    public Tetris() {
        this.music = new Korobeiniki();
        this.screen = new ConsoleScreen(21, 33, true);
        this.controller = new TetrisController(20, 10, TetrominoFactory.random(), TetrominoFactory.random());
        this.screen
                .addPanel(new PlayFieldPanel(controller, Position.at(2, 2), 2))
                .addPanel(new NextTetrominoPanel(controller, Position.at(2, 23)))
                .addPanel(new ScorePanel(controller, Position.at(7, 23)))
                .addPanel(new LinesPanel(controller, Position.at(12, 23)))
                .addPanel(new LevelPanel(controller, Position.at(17, 23)))
                .addPanel(new PausePanel(controller, Position.at(8, 2)));
    }

    public void start() {
        this.music.init();
        this.music.play();
        userInputReader.init();
        screen.init();
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(
                this::loop, 0L, 1000000000L / fps, TimeUnit.NANOSECONDS);
    }

    public void loop() {
        try {
            if(!controller.getState().isPaused()) {
                gameLoop();
            } else {
                pauseLoop();
            }
            screen.render();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    public void gameLoop() {
        if (controller.getState().isGameOver()) {
            System.out.println("Game over");
            System.exit(0);
        }
        if (controller.getState().needToInsert()) {
            controller = controller.insert(TetrominoFactory.random());
        }

        for (Character c : userInputReader.getNextUserInput()) {
            switch (c) {
                case 'h':
                    controller = controller.moveLeft();
                    break;
                case 'k':
                    controller = controller.moveRight();
                    break;
                case 'j':
                    controller = controller.moveDown(true);
                    break;
                case 'u':
                    controller = controller.rotateLeft();
                    break;
                case 'i':
                    controller = controller.rotateRight();
                    break;
                case ' ':
                    controller = controller.drop();
                    break;
                case 'p':
                    controller = controller.pause();
                    break;
                case 'm':
                    if (this.music.isPlaying()) {
                        this.music.pause();
                    } else {
                        this.music.play();
                    }
                    break;
                case 'q':
                    System.exit(0);
                    break;
            }
        }
        frames++;
        if (frames % downFrames == 0) {
            controller = controller.moveDown(false);
        }
        if(level < controller.getState().level()) {
            frames = 0;
            downFrames = getDownFrames(controller.getState().level());
            level = controller.getState().level();
        }
    }

    private void pauseLoop() {
        for (Character c : userInputReader.getNextUserInput()) {
            switch (c) {
                case 'r':
                    controller = controller.resume();
                    break;
                case 'm':
                    if (this.music.isPlaying()) {
                        this.music.pause();
                    } else {
                        this.music.play();
                    }
                    break;
                case 'q':
                    System.exit(0);
                    break;
            }
        }
    }

    private int getDownFrames(int level) {
        return switch (level) {
            case 0 -> 53;
            case 1 -> 49;
            case 2 -> 45;
            case 3 -> 41;
            case 4 -> 37;
            case 5 -> 33;
            case 6 -> 28;
            case 7 -> 22;
            case 8 -> 17;
            case 9 -> 11;
            case 10 -> 10;
            case 11 -> 9;
            case 12 -> 8;
            case 13 -> 7;
            case 14 -> 6;
            case 15 -> 6;
            case 16 -> 5;
            case 17 -> 5;
            case 18 -> 4;
            case 19 -> 4;
            case 20 -> 3;
            default -> throw new IllegalStateException("Unexpected value: " + level);
        };
    }

    public static void main(String[] args) {
        new Tetris().start();
    }
}
