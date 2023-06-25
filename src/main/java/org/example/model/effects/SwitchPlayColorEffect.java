package org.example.model.effects;

import org.example.model.games.Game;

public class SwitchPlayColorEffect extends EffectDecorator {

    public SwitchPlayColorEffect(CardEffect effect) {
        super(effect, 1);
    }

    @Override
    public void executeEffect(Game game) {
        super.executeEffect(game);
        //game.switchColorPrompt(); //NOTE: should game have executeCardBehavior()? NO
    }
}
