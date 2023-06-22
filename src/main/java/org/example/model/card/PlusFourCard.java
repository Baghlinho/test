package org.example.model.card;

import org.example.model.effects.CardEffect;
import org.example.model.effects.CurrentPlayerDrawEffect;
import org.example.model.effects.InitiateNextTurnEffect;
import org.example.model.effects.ProceedNextPlayerEffect;

public class PlusFourCard extends WildCard {
    public PlusFourCard() {
        super(20, "+4");
    }

    @Override
    protected CardEffect buildEffect() {
        CardEffect effect = new InitiateNextTurnEffect();
        effect = new CurrentPlayerDrawEffect(effect, 4);
        return new ProceedNextPlayerEffect(effect, 1);
    }
}
