package org.example.model.rules;

import org.example.model.card.Card;
import org.example.model.card.Color;

public class WildCardRule implements PlayCardRule {
    @Override
    public boolean check(Card card) {
        return card.getColor() == Color.WILD;
    }
}
