package org.example.model.card;

import org.example.model.effects.CardEffect;
import org.example.model.effects.BaseEffect;
import org.example.model.effects.ProceedNextPlayerEffect;

public class SkipCard extends ActionCard {
    public SkipCard(Color color) {
        super(color, 20,"@");
    }

    public SkipCard(SkipCard prototype) {
        super(prototype);
    }

    @Override
    protected CardEffect buildEffect() {
        CardEffect effect = new BaseEffect();
        return new ProceedNextPlayerEffect(effect, 2);
    }

    @Override
    public Card clone() {
        return new SkipCard(this);
    }
}
