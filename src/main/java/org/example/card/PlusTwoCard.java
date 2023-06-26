package org.example.card;

import org.example.effects.CardEffect;
import org.example.effects.CurrentPlayerDrawEffect;
import org.example.effects.ResetDefaultPlayRule;
import org.example.effects.ProceedNextPlayerEffect;

public class PlusTwoCard extends ActionCard {
    public PlusTwoCard(Color color) {
        super(color, 20, "+2");
    }

    protected PlusTwoCard(PlusTwoCard prototype) {
        super(prototype);
    }

    @Override
    protected CardEffect buildEffect() {
        CardEffect effect = new ResetDefaultPlayRule();
        effect = new ProceedNextPlayerEffect(effect, 1);
        effect = new CurrentPlayerDrawEffect(effect, 2);
        return new ProceedNextPlayerEffect(effect, 1);
    }

    @Override
    public Card clone() {
        return new PlusTwoCard(this);
    }
}
