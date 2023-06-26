package org.example.card;

import java.util.Collections;
import java.util.Stack;

public class CardPile {
    private final Stack<Card> cards = new Stack<>();

    public void pushCard(Card card) {
        cards.push(card);
    }

    public Card popCard() {
        return cards.pop();
    }

    public Card peekTopCard() {
        return cards.peek();
    }

    public boolean isEmpty() {
        return cards.empty();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public int size() {
        return cards.size();
    }

    public void transferCard(CardPile cardPile) {
        cardPile.pushCard(this.popCard());
    }
}
