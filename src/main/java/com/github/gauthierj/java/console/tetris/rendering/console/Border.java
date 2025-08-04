package com.github.gauthierj.java.console.tetris.rendering.console;

public class Border {

    private Character topLeft;
    private Character top;
    private Character topRight;
    private Character left;
    private Character right;
    private Character bottomLeft;
    private Character bottom;
    private Character bottomRight;

    public Border(Character topLeft,
                  Character top,
                  Character topRight,
                  Character left,
                  Character right,
                  Character bottomLeft,
                  Character bottom,
                  Character bottomRight) {
        this.topLeft = topLeft;
        this.top = top;
        this.topRight = topRight;
        this.left = left;
        this.right = right;
        this.bottomLeft = bottomLeft;
        this.bottom = bottom;
        this.bottomRight = bottomRight;
    }

    public boolean hasTopBorder() {
        return topLeft != null && topRight != null && top != null;
    }

    public boolean hasBottomBorder() {
        return bottomLeft != null && bottomRight != null && bottom != null;
    }

    public boolean hasLeftBorder() {
        return left != null && topLeft != null && bottomLeft != null;
    }

    public boolean hasRightBorder() {
        return right != null && topRight != null && bottomRight != null;
    }

    public char topLeft() {
        return topLeft;
    }

    public char top() {
        return top;
    }

    public char topRight() {
        return topRight;
    }

    public char left() {
        return left;
    }

    public char right() {
        return right;
    }

    public Character getBottomLeft() {
        return bottomLeft;
    }

    public Character getBottom() {
        return bottom;
    }

    public Character getBottomRight() {
        return bottomRight;
    }
}
