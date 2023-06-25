package org.example.model.effects;

import org.example.model.games.Game;

public class ReverseDirectionEffect extends EffectDecorator {

    public ReverseDirectionEffect(CardEffect effect) {
        super(effect, 1);
    }

    @Override
    public void executeEffect(Game game) {
        super.executeEffect(game);
        game.reverseDirection();
    }
}
