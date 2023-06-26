package org.example.model.card;

import org.example.model.effects.CardEffect;
import org.example.model.effects.BaseEffect;
import org.example.model.effects.ProceedNextPlayerEffect;
import org.example.model.effects.SelectPlayColorEffect;

public class Wild extends WildCard {
    public Wild() {
        super(50, "W");
    }

    public Wild(Wild prototype) {
        super(prototype);
    }

    @Override
    protected CardEffect buildEffect() {
        CardEffect effect = new BaseEffect();
        effect = new SelectPlayColorEffect(effect);
        return new ProceedNextPlayerEffect(effect, 1);
    }

    @Override
    public Card clone() {
        return new Wild(this);
    }
}
