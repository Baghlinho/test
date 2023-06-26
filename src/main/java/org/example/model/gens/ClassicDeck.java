package org.example.model.gens;

import org.example.model.card.Color;

public class ClassicDeck extends PredefinedDeck {
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
