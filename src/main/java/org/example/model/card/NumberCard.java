package org.example.model.card;

import org.example.model.effects.CardEffect;
import org.example.model.effects.InitiateNextTurnEffect;

public class NumberCard extends Card {

    public NumberCard(Color color, int value) {
        super(color, value, String.valueOf(value));
    }

    @Override
    protected CardEffect buildEffect() {
        return new InitiateNextTurnEffect();
    }
}
