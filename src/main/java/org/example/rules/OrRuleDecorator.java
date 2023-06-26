package org.example.rules;

import org.example.card.Card;

public abstract class OrRuleDecorator implements PlayCardRule {

    private final PlayCardRule playCardRule;

    public OrRuleDecorator(PlayCardRule playCardRule) {
        this.playCardRule = playCardRule;
    }

    @Override
    public boolean check(Card card) {
        return playCardRule.check(card);
    }
}
