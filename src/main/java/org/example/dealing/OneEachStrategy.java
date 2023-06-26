package org.example.dealing;

import org.example.game.Game;

public class OneEachStrategy implements DealStrategy {
    @Override
    public void deal(Game game) {
        for (int i = 0; i < game.getEachDealtCount(); i++) {
            for (int j = 0; j < game.getPlayersCount(); j++) {
                game.drawCards(1);
                game.proceedNextPlayer();
            }
        }
    }
}
