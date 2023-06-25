package org.example.model.dealing;

import org.example.model.Player;
import org.example.model.games.Game;

public class OneEachStrategy implements DealStrategy {
    @Override
    public void deal(Game game) {
        for (int i = 0; i < game.getCardsEach(); i++) {
            for (int j = 0; j < game.getPlayersCount(); j++) {
                game.drawCards(1);
                game.nextPlayer();
            }
        }
    }
}
