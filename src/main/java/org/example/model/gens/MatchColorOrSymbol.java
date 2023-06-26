package org.example.model.gens;

import org.example.model.card.Color;
import org.example.model.rules.MatchColorRule;
import org.example.model.rules.MatchSymbolRule;
import org.example.model.rules.PlayCardRule;
import org.example.model.rules.WildCardRule;

public class MatchColorOrSymbol implements RuleSetGenerator {
    private Color color;
    private String symbol;

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

    public void setColor(Color color) {
        this.color = color;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
