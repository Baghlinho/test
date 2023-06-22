package org.example.model.card;

import org.example.model.effects.CardEffect;
import org.example.model.effects.InitiateNextTurnEffect;
import org.example.model.effects.ProceedNextPlayerEffect;

public class SkipCard extends ActionCard {
    public SkipCard(Color color) {
        super(color, 20,"@");
    }

    @Override
    protected CardEffect buildEffect() {
        CardEffect effect = new InitiateNextTurnEffect();
        return new ProceedNextPlayerEffect(effect, 1);
    }
}
