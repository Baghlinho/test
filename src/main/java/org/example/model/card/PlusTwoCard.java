package org.example.model.card;

import org.example.model.effects.CardEffect;
import org.example.model.effects.CurrentPlayerDrawEffect;
import org.example.model.effects.BaseEffect;
import org.example.model.effects.ProceedNextPlayerEffect;

public class PlusTwoCard extends ActionCard {
    public PlusTwoCard(Color color) {
        super(color, 20, "+2");
    }

    public PlusTwoCard(PlusTwoCard prototype) {
        super(prototype);
    }

    @Override
    protected CardEffect buildEffect() {
        CardEffect effect = new BaseEffect();
        effect = new ProceedNextPlayerEffect(effect, 1);
        effect = new CurrentPlayerDrawEffect(effect, 2);
        return new ProceedNextPlayerEffect(effect, 1);
    }

    @Override
    public Card clone() {
        return new PlusTwoCard(this);
    }
}
