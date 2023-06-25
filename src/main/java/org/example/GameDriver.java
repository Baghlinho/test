package org.example;

import org.example.controller.GameController;
import org.example.model.games.ClassicGame;
import org.example.model.games.Game;
import org.example.view.GameView;

public class GameDriver {
    public static void main(String[] args) {
        Game game = new ClassicGame();
        game.play();
    }
}
