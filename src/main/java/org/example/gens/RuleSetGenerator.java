package org.example.gens;

import org.example.rules.PlayCardRule;

public interface RuleSetGenerator {
    PlayCardRule[] generateRuleSet();
}
