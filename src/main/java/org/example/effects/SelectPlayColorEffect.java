package org.example.effects;

import org.example.game.Game;

public class SelectPlayColorEffect extends EffectDecorator {

    public SelectPlayColorEffect(CardEffect effect) {
        super(effect, 1);
    }

    @Override
    public void executeEffect(Game game) {
        super.executeEffect(game);
        game.selectPlayColor();
    }
}
