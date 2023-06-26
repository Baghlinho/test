package org.example.model.rules;

import org.example.model.card.Card;
import org.example.model.card.Color;

public class MatchColorRule extends OrRuleDecorator {

    private final Color color;

    public MatchColorRule(PlayCardRule rule, Color color) {
        super(rule);
        this.color = color;
    }

    @Override
    public boolean check(Card card) {
        return super.check(card) || color == card.getColor() || color == Color.WILD;
    }
}
