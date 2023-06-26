package org.example;

import org.example.model.games.ClassicGame;
import org.example.model.games.Game;

public class GameDriver {
    public static void main(String[] args) {
        Game game = new ClassicGame();
        game.play();
    }
}
