package org.example.model.card;

import org.example.model.effects.CardEffect;
import org.example.model.effects.BaseEffect;
import org.example.model.effects.ProceedNextPlayerEffect;
import org.example.model.effects.ReverseDirectionEffect;

public class ReverseCard extends ActionCard {
    public ReverseCard(Color color) {
        super(color, 20, "<-");
    }

    public ReverseCard(ReverseCard prototype) {
        super(prototype);
    }

    @Override
    protected CardEffect buildEffect() {
        CardEffect effect = new BaseEffect();
        effect = new ReverseDirectionEffect(effect);
        return new ProceedNextPlayerEffect(effect, 1);
    }

    @Override
    public Card clone() {
        return new ReverseCard(this);
    }
}
