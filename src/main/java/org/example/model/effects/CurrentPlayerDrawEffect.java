package org.example.model.effects;

import org.example.model.games.Game;

public class CurrentPlayerDrawEffect extends EffectDecorator {

    public CurrentPlayerDrawEffect(CardEffect effect, int times) {
        super(effect, times);
    }

    @Override
    public void executeEffect(Game game) {
        super.executeEffect(game);
        game.drawCards(getTimes());
    }
}
