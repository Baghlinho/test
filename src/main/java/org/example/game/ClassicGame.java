package org.example.game;

import org.example.card.Color;
import org.example.dealing.AllAtOnceStrategy;
import org.example.gens.ClassicDeckGenerator;
import org.example.gens.MatchColorOrSymbol;
import org.example.rules.PlayCardRule;
import org.example.ui.ConsoleInterface;

public class ClassicGame extends Game {

    public ClassicGame() {
        super(7,
                1,
                500,
                1,
                4,
                new ClassicDeckGenerator(),
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
