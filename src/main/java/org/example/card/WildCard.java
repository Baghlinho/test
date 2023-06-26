package org.example.card;

public abstract class WildCard extends Card{

    protected WildCard(int value, String symbol) {
        super(Color.WILD, value, symbol);
    }

    protected WildCard(WildCard prototype){
        super(prototype);
    }
}
