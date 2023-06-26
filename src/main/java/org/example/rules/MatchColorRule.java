package org.example.rules;

import org.example.card.Card;
import org.example.card.Color;

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
