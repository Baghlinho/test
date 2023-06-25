package org.example.model.games;

import org.example.model.card.Color;
import org.example.model.dealing.AllAtOnceStrategy;
import org.example.model.dealing.DealStrategy;

public class ClassicGame extends Game {
    public ClassicGame() {
        super(7);
    }

//    MAKE THIS A DEFAULT DECK SETUP
    @Override
    protected void setUpDrawPile() {
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
        drawPile.shuffle();
    }

    @Override
    protected DealStrategy getDealStrategy() {
        return new AllAtOnceStrategy();
    }
}
