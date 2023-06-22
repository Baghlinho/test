package org.example.model.effects;

import org.example.model.card.*;

public class CardFactory {
    public Card createNumberCard(Color color, int value) {
        return new NumberCard(color, value);
    }
    public Card createWildCard(WildType type) {
        switch (type) {
            case Wild:
                return new Wild();
            case WildFour:
                return new PlusFourCard();
            default:
                throw new IllegalArgumentException("Wild type can't be null");
        }
    }
    public Card createActionCard(Color color, ActionType type) {
        switch (type) {
            case DrawTwo:
                return new PlusTwoCard(color);
            case Skip:
                return new SkipCard(color);
            case Reverse:
                return new ReverseCard(color);
            default:
                throw new IllegalArgumentException("Action type can't be null");
        }
    }
}
