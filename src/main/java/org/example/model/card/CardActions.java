package org.example.model.card;

import org.example.model.games.Game;

public class CardActions {
    private Game game;

    public void skip() {
//        game.nextTurn();
    }

    public void draw(int times) {
        for (int i = 0; i < times; i++) {
//            game.drawCard();
        }
    }

    public void reverse() {
//        game.reverseTurn();
    }

    public void switchColor() {
//        game.switchColor();
    }
}
