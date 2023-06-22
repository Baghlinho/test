package org.example.model.card;

import org.example.model.Game;
import org.example.model.effects.CardEffect;

import java.util.Objects;

public abstract class Card {
    private final int value;
    private final Color color;
    private final String symbol;
    private final CardEffect effect;

    protected Card(Color color, int value, String symbol) {
        this.color = color;
        this.value = value;
        this.symbol = symbol;
        effect = buildEffect();
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
        return color+symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (value != card.value) return false;
        if (color != card.color) return false;
        return Objects.equals(symbol, card.symbol);
    }

    @Override
    public int hashCode() {
        int result = value;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (symbol != null ? symbol.hashCode() : 0);
        return result;
    }

    protected abstract CardEffect buildEffect();

    public void executeEffect(Game game) {
        effect.executeEffect(game);
    }

}
