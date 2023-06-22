package org.example.model.effects;

import org.example.model.Game;

public class CurrentPlayerDrawEffect extends EffectDecorator {

    public CurrentPlayerDrawEffect(CardEffect effect, int times) {
        super(effect, times);
    }

    @Override
    public void executeEffect(Game game) {
        super.executeEffect(game);
        for (int i = 0; i < getTimes(); i++) {
            // game.drawCardCurrentPlayer();
            // NOTE: make the method take the number of cards to be drawn
        }
    }
}
