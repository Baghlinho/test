package org.example.model.games;

import org.example.model.card.Color;
import org.example.model.dealing.AllAtOnceStrategy;
import org.example.model.gens.ClassicDeck;
import org.example.model.gens.MatchColorOrSymbol;
import org.example.model.rules.PlayCardRule;
import org.example.view.ConsoleInterface;

public class ClassicGame extends Game {

    public ClassicGame() {
        super(7,
                3,
                500,
                1,
                4,
                new ClassicDeck(),
                new ConsoleInterface(),
                new AllAtOnceStrategy(),
                PlayDirection.LEFT
                );
    }

    @Override
    protected PlayCardRule[] getDefaultPlayCardRule() {
        Color colorToMatch = getLastCardDiscarded().getColor();
        String symbolToMatch = getLastCardDiscarded().getSymbol();
        return new MatchColorOrSymbol(colorToMatch, symbolToMatch).generateRuleSet();
    }
}
