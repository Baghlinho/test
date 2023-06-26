package org.example.gens;

import org.example.card.Color;
import org.example.rules.MatchColorRule;
import org.example.rules.PlayCardRule;
import org.example.rules.WildCardRule;

public class MatchColorOnly implements RuleSetGenerator {
    private final Color color;

    public MatchColorOnly(Color color) {
        this.color = color;
    }

    @Override
    public PlayCardRule[] generateRuleSet() {
        PlayCardRule rule = new WildCardRule();
        rule = new MatchColorRule(rule, color);
        return new PlayCardRule[]{rule};
    }
}
