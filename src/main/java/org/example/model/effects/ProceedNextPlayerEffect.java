package org.example.model.effects;

import org.example.model.games.Game;

public class ProceedNextPlayerEffect extends EffectDecorator {

    public ProceedNextPlayerEffect(CardEffect effect, int times) {
        super(effect, times);
    }

    @Override
    public void executeEffect(Game game) {
        super.executeEffect(game);
        for (int i = 0; i < getTimes(); i++) {
            game.nextPlayer();
        }
    }
}
