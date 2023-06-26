package org.example.dealing;

import org.example.game.Game;

public class AllAtOnceStrategy implements DealStrategy {
    @Override
    public void deal(Game game) {
        for (int i = 0; i < game.getPlayersCount(); i++) {
            game.drawCards(game.getEachDealtCount());
            game.proceedNextPlayer();
        }
    }
}
