package org.example.card;

import org.example.effects.*;

public class PlusFourCard extends WildCard {
    public PlusFourCard() {
        super(50, "+4");
    }

    protected PlusFourCard(PlusFourCard prototype) {
        super(prototype);
    }

    @Override
    protected CardEffect buildEffect() {
        CardEffect effect = new ResetDefaultPlayRule();
        effect = new SelectPlayColorEffect(effect);
        effect = new ProceedNextPlayerEffect(effect, 1);
        effect = new CurrentPlayerDrawEffect(effect, 4);
        return new ProceedNextPlayerEffect(effect, 1);
    }

    @Override
    public Card clone() {
        return new PlusFourCard(this);
    }
}
