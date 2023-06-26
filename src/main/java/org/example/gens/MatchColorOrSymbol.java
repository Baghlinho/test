package org.example.gens;

import org.example.card.Color;
import org.example.rules.MatchColorRule;
import org.example.rules.MatchSymbolRule;
import org.example.rules.PlayCardRule;
import org.example.rules.WildCardRule;

public class MatchColorOrSymbol implements RuleSetGenerator {
    private final Color color;
    private final String symbol;

    public MatchColorOrSymbol(Color color, String symbol) {
        this.color = color;
        this.symbol = symbol;
    }

    @Override
    public PlayCardRule[] generateRuleSet() {
        PlayCardRule rule = new WildCardRule();
        rule = new MatchSymbolRule(rule, symbol);
        rule = new MatchColorRule(rule, color);
        return new PlayCardRule[]{rule};
    }
}
