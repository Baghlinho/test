package org.example.gens;

import org.example.card.CardPile;
import org.example.card.Card;
import org.example.card.Color;

public abstract class PredefinedDeckGenerator {
    private final CardPile cardPile = new CardPile();

    protected abstract void buildDeck();

    public CardPile generateDeck() {
        buildDeck();
        return cardPile;
    }

    protected final void addWildCard(String type, int count) {
        Card card = CardFactory.createWildCard(type);
        addCardToDeck(card, count);
    }

    protected final void addActionCard(String type, Color color, int count) {
        Card card = CardFactory.createActionCard(type, color);
        addCardToDeck(card, count);
    }

    protected final void addNumberCard(Color color, int value, int count) {
        Card card = CardFactory.createNumberCard(color, value);
        addCardToDeck(card, count);
    }

    protected final void shuffle(){
        cardPile.shuffle();
    }

    private void addCardToDeck(Card card, int count) {
        cardPile.pushCard(card);
        for (int i = 0; i < count - 1; i++) {
            cardPile.pushCard(card.clone());
        }
    }
}
