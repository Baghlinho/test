package org.example.model.games;

public enum PlayDirection {
    LEFT(1),
    RIGHT(-1);
    private final int value;

    PlayDirection(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
