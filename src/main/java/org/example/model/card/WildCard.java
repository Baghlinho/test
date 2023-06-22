package org.example.model.card;

public abstract class WildCard extends Card{

    protected WildCard(int value, String symbol) {
        super(Color.NONE, value, symbol);
    }
}
