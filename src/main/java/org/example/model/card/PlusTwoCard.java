package org.example.model.card;

import org.example.model.effects.CardEffect;
import org.example.model.effects.CurrentPlayerDrawEffect;
import org.example.model.effects.InitiateNextTurnEffect;
import org.example.model.effects.ProceedNextPlayerEffect;

public class PlusTwoCard extends ActionCard {
    public PlusTwoCard(Color color) {
        super(color, 20, "+2");
    }

    @Override
    protected CardEffect buildEffect() {
        CardEffect effect = new InitiateNextTurnEffect();
        effect = new CurrentPlayerDrawEffect(effect, 2);
        return new ProceedNextPlayerEffect(effect, 1);
    }
}
