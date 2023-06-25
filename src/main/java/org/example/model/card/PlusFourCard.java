package org.example.model.card;

import org.example.model.effects.*;

public class PlusFourCard extends WildCard {
    public PlusFourCard() {
        super(20, "+4");
    }

    public PlusFourCard(PlusFourCard prototype) {
        super(prototype);
    }

    @Override
    protected CardEffect buildEffect() {
        CardEffect effect = new BaseEffect();
        effect = new SwitchPlayColorEffect(effect);
        effect = new ProceedNextPlayerEffect(effect, 1);
        effect = new CurrentPlayerDrawEffect(effect, 4);
        return new ProceedNextPlayerEffect(effect, 1);

    }

    @Override
    public Card clone() {
        return new PlusFourCard(this);
    }
}
