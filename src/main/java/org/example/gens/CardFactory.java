package org.example.gens;

import org.example.card.*;

public class CardFactory {
    public static Card createNumberCard(Color color, int value) {
        return new NumberCard(color, value);
    }
    public static Card createWildCard(String type) {
        switch (type) {
            case "Wild":
                return new Wild();
            case "WildFour":
                return new PlusFourCard();
            default:
                throw new IllegalArgumentException("Invalid wild card type");
        }
    }
    public static Card createActionCard(String type, Color color) {
        switch (type) {
            case "DrawTwo":
                return new PlusTwoCard(color);
            case "Skip":
                return new SkipCard(color);
            case "Reverse":
                return new ReverseCard(color);
            default:
                throw new IllegalArgumentException("Invalid action card type");
        }
    }
}
