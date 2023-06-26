package org.example.model.dealing;

import org.example.model.games.Game;

public class AllAtOnceStrategy implements DealStrategy {
    @Override
    public void deal(Game game) {
        for (int i = 0; i < game.getPlayersCount(); i++) {
            game.drawCards(game.getDealtCards());
            game.nextPlayer();
        }
    }
}
