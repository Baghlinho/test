package org.example.model.card;

import org.example.model.games.Game;
import org.example.model.effects.CardEffect;

import java.util.HashMap;
import java.util.Map;

public abstract class Card {
    private final int value;
    private final Color color;
    private final String symbol;
    private static final Map<String, String> symbols = new HashMap<>();
    private CardEffect effect;

    protected Card(Color color, int value, String symbol) { // throws IllegalArgumentException() {
        this.color = color;
        this.value = value;
        this.symbol = symbol;
        checkCardValidity();
        effect = buildEffect();
    }

    protected Card(Card prototype){
        this(prototype.color, prototype.value, prototype.symbol);
        effect = prototype.effect;
    }

    private void checkCardValidity() {
        if(symbol == null || color == null)
            throw new IllegalArgumentException("symbol and color of card can't be null");
        String concreteClass = getClass().getName();
        if(symbols.containsValue(symbol) && !symbols.containsKey(concreteClass))
            throw new IllegalArgumentException("different cards cannot have the same symbol");
        symbols.put(concreteClass, symbol);
    }

    public Color getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }

    public String getSymbol(){
        return symbol;
    }

    @Override
    public String toString() {
        return color+symbol+"\u001B[0m";
    }

    public void executeEffect(Game game) {
        effect.executeEffect(game);
    }

    protected abstract CardEffect buildEffect();
    public abstract Card clone();
}
