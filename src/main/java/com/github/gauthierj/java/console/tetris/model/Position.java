package com.github.gauthierj.java.console.tetris.model;

import com.github.gauthierj.java.console.tetris.util.ImmutableStyle;
import org.immutables.value.Value;

@ImmutableStyle
@Value.Immutable
public interface Position {

    @Value.Parameter(order = 0)
    int row();
    @Value.Parameter(order = 1)
    int column();

    static Position origin() {
        return at(0, 0);
    }

    static Position at(int row, int column) {
        return PositionImpl.of(row, column);
    }

    default Position relativeTo(Position position) {
        return at(this.row() + position.row(), this.column() + position.column());
    }

    default Position down() {
        return down(1);
    }

    default Position down(int count) {
        return at(this.row() + count, this.column());
    }

    default Position left() {
        return left(1);
    }

    default Position left(int count) {
        return at(this.row(), this.column() - count);
    }

    default Position right() {
        return right(1);
    }

    default Position right(int count) {
        return at(this.row(), this.column() + count);
    }
}
