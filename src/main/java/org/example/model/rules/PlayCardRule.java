package org.example.model.rules;

import org.example.model.card.Card;

public interface PlayCardRule {
    boolean check(Card card);
}
