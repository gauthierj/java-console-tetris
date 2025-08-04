package com.github.gauthierj.java.console.tetris;

import com.github.gauthierj.java.console.tetris.model.PlayField;
import com.github.gauthierj.java.console.tetris.model.Tetromino;

import java.util.List;

public class TetrisController implements TetrisStateHolder {

    private TetrisState state;

    public TetrisController(int rows,
                            int columns,
                            Tetromino currentTetromino,
                            Tetromino nextTetromino) {
        PlayField playField = PlayField.ofSize(rows, columns);
        this.state = TetrisStateImpl.of(
                playField,
                playField.topPosition(currentTetromino),
                currentTetromino,
                nextTetromino);
    }

    public TetrisController insert(Tetromino tetromino) {
        this.state = TetrisStateImpl.copyOf(state)
                .withNeedToInsert(false)
                .withCurrentPosition(this.state.playField().topPosition(state.nextTetromino()))
                .withCurrentTetromino(state.nextTetromino())
                .withNextTetromino(tetromino);
        return this;
    }

    public TetrisController moveDown(boolean softDrop) {
        if (state.playField().canInsert(state.currentTetromino(), state.currentPosition().down())) {
            this.state = TetrisStateImpl.copyOf(state)
                    .withCurrentPosition(state.currentPosition().down())
                    .withSoftDropCount(softDrop ? state.softDropCount() + 1 : state.softDropCount());
        } else {
            this.state = TetrisStateImpl.copyOf(state)
                    .withPlayField(this.state.playField().insert(this.state.currentTetromino(), this.state.currentPosition()))
                    .withNeedToInsert(true)
                    .withScore(this.state.score() + this.state.softDropCount())
                    .withSoftDropCount(0);

            List<Integer> fullRows = this.state.playField().getFullRows();
            if(!fullRows.isEmpty()) {
                this.state = TetrisStateImpl.copyOf(state)
                        .withLines(this.state.lines() + fullRows.size())
                        .withScore(this.state.score() + (this.state.level() + 1) * score(fullRows.size()))
                        .withPlayField(this.state.playField().wipeRows(fullRows))
                        .withLevel(this.state.level() < 20 ? Math.max(this.state.lines() / 10, this.state.level()) : this.state.level());
            }
        }
        return this;
    }

    public TetrisController drop() {
        TetrisController result = this;
        while (this.state.playField().canInsert(this.state.currentTetromino(), this.state.currentPosition().down())) {
            result = this.moveDown(true);
        }
        return result;
    }

    public TetrisController moveLeft() {
        if (this.state.playField().canInsert(this.state.currentTetromino(), this.state.currentPosition().left())) {
            this.state = TetrisStateImpl.copyOf(state)
                    .withCurrentPosition(this.state.currentPosition().left());
        }
        return this;
    }

    public TetrisController moveRight() {
        if (this.state.playField().canInsert(this.state.currentTetromino(), this.state.currentPosition().right())) {
            this.state = TetrisStateImpl.copyOf(this.state)
                    .withCurrentPosition(this.state.currentPosition().right());
        }
        return this;
    }

    public TetrisController rotateLeft() {
        if (this.state.playField().canInsert(this.state.currentTetromino().rotateLeft(), this.state.currentPosition())) {
            this.state = TetrisStateImpl.copyOf(this.state)
                    .withCurrentTetromino(this.state.currentTetromino().rotateLeft());
        }
        return this;
    }

    public TetrisController rotateRight() {
        if (this.state.playField().canInsert(this.state.currentTetromino().rotateRight(), this.state.currentPosition())) {
            this.state = TetrisStateImpl.copyOf(this.state)
                    .withCurrentTetromino(this.state.currentTetromino().rotateRight());
        }
        return this;
    }

    public TetrisController pause() {
        this.state = TetrisStateImpl.copyOf(this.state)
                .withIsPaused(true);
        return this;
    }

    public TetrisController resume() {
        this.state = TetrisStateImpl.copyOf(this.state)
                .withIsPaused(false);
        return this;
    }

    @Override
    public TetrisState getState() {
        return this.state;
    }

    private int score(int numberOfLines) {
        return switch (numberOfLines) {
            case 0 -> 0;
            case 1 -> 40;
            case 2 -> 100;
            case 3 -> 300;
            case 4 -> 1200;
            default -> throw new IllegalStateException("Unexpected value: " + numberOfLines);
        };
    }
}
