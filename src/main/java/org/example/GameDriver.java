package org.example;

import org.example.controller.GameController;
import org.example.model.Game;
import org.example.view.GameView;

public class GameDriver {
    public static void main(String[] args) {
        Game game = setUpGame();
        game.play();
    }

    private static Game setUpGame() {
        Game game = new Game();

        GameController controller = new GameController(game);
        GameView view = new GameView(controller);
        return game;
    }
}
