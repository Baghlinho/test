package org.example.effects;

import org.example.game.Game;

public class ResetDefaultPlayRule implements CardEffect{
    @Override
    public void executeEffect(Game game) {
        game.resetPlayRule();
    }
}
