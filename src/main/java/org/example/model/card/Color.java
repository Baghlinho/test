package org.example.model.card;

public enum Color {
    RED("R"),
    BLUE("B"),
    YELLOW("Y"),
    GREEN("G"),
    NONE("-");

    private final String symbol;
    private Color(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
