package org.example.controller;

import org.example.model.Player;
import org.example.model.games.Game;
import org.example.view.View;

public class GameController {
    private final Game model;
    private View view;

    public GameController(Game model) {
        this.model = model;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void initializePlayers(int count, String[] names) {
//        model.initializePlayers(count, names);
    }

    public void discardCard(String cardSymbol, Player player) {
//        model.discardCard(cardSymbol, player);
    }

    public void startGame() {
        model.play();
    }
}
