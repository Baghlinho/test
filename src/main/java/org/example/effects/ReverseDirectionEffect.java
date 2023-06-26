package org.example.effects;

import org.example.game.Game;

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
