package org.example.card;

import org.example.effects.SelectPlayColorEffect;
import org.example.effects.CardEffect;
import org.example.effects.ResetDefaultPlayRule;
import org.example.effects.ProceedNextPlayerEffect;

public class Wild extends WildCard {
    public Wild() {
        super(50, "W");
    }

    protected Wild(Wild prototype) {
        super(prototype);
    }

    @Override
    protected CardEffect buildEffect() {
        CardEffect effect = new ResetDefaultPlayRule();
        effect = new SelectPlayColorEffect(effect);
        return new ProceedNextPlayerEffect(effect, 1);
    }

    @Override
    public Card clone() {
        return new Wild(this);
    }
}
