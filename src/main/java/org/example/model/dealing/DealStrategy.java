package org.example.model.dealing;

import org.example.model.Player;
import org.example.model.games.Game;

public interface DealStrategy {
    void deal(Game game);
}
