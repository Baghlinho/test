package org.example.model.card;

import org.example.model.effects.CardEffect;
import org.example.model.effects.BaseEffect;
import org.example.model.effects.ProceedNextPlayerEffect;
import org.example.model.effects.SwitchPlayColorEffect;

public class Wild extends WildCard {
    public Wild() {
        super(20, "W");
    }

    public Wild(Wild prototype) {
        super(prototype);
    }

    @Override
    protected CardEffect buildEffect() {
        CardEffect effect = new BaseEffect();
        effect = new SwitchPlayColorEffect(effect);
        return new ProceedNextPlayerEffect(effect, 1);
    }

    @Override
    public Card clone() {
        return new Wild(this);
    }
}
