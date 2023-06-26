package org.example.model.gens;

import org.example.model.card.Color;
import org.example.model.rules.MatchColorRule;
import org.example.model.rules.PlayCardRule;
import org.example.model.rules.WildCardRule;

public class MatchColorOnly implements RuleSetGenerator {
    private Color color;

    public MatchColorOnly(Color color) {
        this.color = color;
    }

    @Override
    public PlayCardRule[] generateRuleSet() {
        PlayCardRule rule = new WildCardRule();
        rule = new MatchColorRule(rule, color);
        return new PlayCardRule[]{rule};
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
