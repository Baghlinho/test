package org.example.model.gens;

import org.example.model.rules.PlayCardRule;

public interface RuleSetGenerator {
    PlayCardRule[] generateRuleSet() throws NullPointerException;
}
