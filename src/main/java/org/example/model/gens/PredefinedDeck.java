package org.example.model.gens;

import org.example.model.CardPile;
import org.example.model.card.Card;
import org.example.model.card.Color;

public abstract class PredefinedDeck {
    private final CardFactory cardFactory = new CardFactory();
    private final CardPile cardPile = new CardPile();

    protected abstract void buildDeck();

    public CardPile generateDeck() {
        buildDeck();
        return cardPile;
    }

    protected final void addWildCard(String type, int count) {
        Card card = cardFactory.createWildCard(type);
        addCardToDeck(card, count);
    }

    protected final void addActionCard(String type, Color color, int count) {
        Card card = cardFactory.createActionCard(type, color);
        addCardToDeck(card, count);
    }

    protected final void addNumberCard(Color color, int value, int count) {
        Card card = cardFactory.createNumberCard(color, value);
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
