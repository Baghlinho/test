package org.example.model.card;

import org.example.model.effects.CardEffect;
import org.example.model.effects.BaseEffect;
import org.example.model.effects.ProceedNextPlayerEffect;

public class NumberCard extends Card {

    public NumberCard(Color color, int value) {
        super(color, value, String.valueOf(value));
    }

    protected NumberCard(NumberCard prototype){
        super(prototype);
    }

    @Override
    protected CardEffect buildEffect() {
        CardEffect effect = new BaseEffect();
        return new ProceedNextPlayerEffect(effect, 1);
    }

    @Override
    public Card clone() {
        return new NumberCard(this);
    }
}
