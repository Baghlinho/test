package org.example.model.card;

import org.example.model.effects.CardEffect;
import org.example.model.effects.InitiateNextTurnEffect;
import org.example.model.effects.SwitchPlayColorEffect;

public class Wild extends WildCard {
    public Wild() {
        super(20, "W");
    }

    @Override
    protected CardEffect buildEffect() {
        CardEffect effect = new InitiateNextTurnEffect();
        return new SwitchPlayColorEffect(effect);
    }
}
