package org.example.gens;

import org.example.card.Color;

public class ClassicDeckGenerator extends PredefinedDeckGenerator {
    @Override
    protected void buildDeck() {
        for(Color color : Color.values()) {
            if(color == Color.WILD)
                continue;
            addNumberCard(color, 0, 1);
            for (int i = 1; i < 10; i++) {
                addNumberCard(color, i, 2);
            }
            addActionCard("Skip", color, 2);
            addActionCard("Reverse", color, 2);
            addActionCard("DrawTwo", color, 2);
        }
        addWildCard("Wild", 4);
        addWildCard("WildFour", 4);
        shuffle();
    }
}
