package org.example.card;

public enum Color {
    RED("\u001B[31m"),
    BLUE("\u001B[34m"),
    YELLOW("\u001B[33m"),
    GREEN("\u001B[32m"),
    WILD("\u001B[45m\u001B[30m");

    private final String code;
    Color(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
