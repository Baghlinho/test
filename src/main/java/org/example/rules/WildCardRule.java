package org.example.rules;

import org.example.card.Card;
import org.example.card.Color;

public class WildCardRule implements PlayCardRule {
    @Override
    public boolean check(Card card) {
        return card.getColor() == Color.WILD;
    }
}
