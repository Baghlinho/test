package org.example.card;

public abstract class ActionCard extends Card {

    protected ActionCard(Color color, int value, String symbol) {
        super(color, value, symbol);
    }

    protected ActionCard(ActionCard prototype){
        super(prototype);
    }
}
