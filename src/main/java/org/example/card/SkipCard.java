package org.example.card;

import org.example.effects.CardEffect;
import org.example.effects.ResetDefaultPlayRule;
import org.example.effects.ProceedNextPlayerEffect;

public class SkipCard extends ActionCard {
    public SkipCard(Color color) {
        super(color, 20,"@");
    }

    protected SkipCard(SkipCard prototype) {
        super(prototype);
    }

    @Override
    protected CardEffect buildEffect() {
        CardEffect effect = new ResetDefaultPlayRule();
        return new ProceedNextPlayerEffect(effect, 2);
    }

    @Override
    public Card clone() {
        return new SkipCard(this);
    }
}
