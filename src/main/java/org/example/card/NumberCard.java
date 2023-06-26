package org.example.card;

import org.example.effects.CardEffect;
import org.example.effects.ResetDefaultPlayRule;
import org.example.effects.ProceedNextPlayerEffect;

public class NumberCard extends Card {

    public NumberCard(Color color, int value) {
        super(color, value, String.valueOf(value));
    }

    protected NumberCard(NumberCard prototype){
        super(prototype);
    }

    @Override
    protected CardEffect buildEffect() {
        CardEffect effect = new ResetDefaultPlayRule();
        return new ProceedNextPlayerEffect(effect, 1);
    }

    @Override
    public Card clone() {
        return new NumberCard(this);
    }
}
