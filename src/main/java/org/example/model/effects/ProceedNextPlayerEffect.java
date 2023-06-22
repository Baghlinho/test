package org.example.model.effects;

import org.example.model.Game;

public class ProceedNextPlayerEffect extends EffectDecorator {

    public ProceedNextPlayerEffect(CardEffect effect, int times) {
        super(effect, times);
    }

    @Override
    public void executeEffect(Game game) {
        super.executeEffect(game);
        for (int i = 0; i < getTimes(); i++) {
//            game.incrementPlayer();
        }
    }

    //NOTE : choose player prompt effect is a different class that can be extended
}
