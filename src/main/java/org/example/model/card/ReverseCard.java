package org.example.model.card;

import org.example.model.effects.CardEffect;
import org.example.model.effects.InitiateNextTurnEffect;
import org.example.model.effects.ReverseDirectionEffect;

public class ReverseCard extends ActionCard {
    public ReverseCard(Color color) {
        super(color, 20, "<-");
    }

    @Override
    protected CardEffect buildEffect() {
        CardEffect effect = new InitiateNextTurnEffect();
        return new ReverseDirectionEffect(effect);
    }
}
