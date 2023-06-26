package org.example;

import org.example.game.ClassicGame;
import org.example.game.Game;

public class GameDriver {
    public static void main(String[] args) {
        Game game = new ClassicGame();
        game.play();
    }
}
