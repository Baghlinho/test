package org.example.card;

import org.example.game.Game;
import org.example.effects.CardEffect;

import java.util.HashMap;
import java.util.Map;

public abstract class Card {
    private final int value;
    private final Color color;
    private final String symbol;
    private CardEffect effect;
    private static final Map<String, String> symbols = new HashMap<>();

    protected Card(Color color, int value, String symbol) {
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
        if(color == Color.WILD && !(this instanceof WildCard))
            throw new IllegalArgumentException("only wild cards can have the wild color attribute");
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

    public static String[] getAllSymbols () {
        String[] symbolsArray = new String[symbols.size()];
        symbols.values().toArray(symbolsArray);
        return symbolsArray;
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
