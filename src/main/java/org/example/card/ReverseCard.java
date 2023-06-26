package org.example.card;

import org.example.effects.CardEffect;
import org.example.effects.ResetDefaultPlayRule;
import org.example.effects.ProceedNextPlayerEffect;
import org.example.effects.ReverseDirectionEffect;

public class ReverseCard extends ActionCard {
    public ReverseCard(Color color) {
        super(color, 20, "<-");
    }

    protected ReverseCard(ReverseCard prototype) {
        super(prototype);
    }

    @Override
    protected CardEffect buildEffect() {
        CardEffect effect = new ResetDefaultPlayRule();
        effect = new ReverseDirectionEffect(effect);
        return new ProceedNextPlayerEffect(effect, 1);
    }

    @Override
    public Card clone() {
        return new ReverseCard(this);
    }
}
