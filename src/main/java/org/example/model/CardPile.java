package org.example.model;

import org.example.model.card.Card;

import java.util.Collections;
import java.util.Stack;

public class CardPile {
    private final Stack<Card> cards = new Stack<>();

    public void pushCard(Card card) {
//        if(card == null)
//            throw new IllegalArgumentException("Card can't be null");
        cards.push(card);
    }

    public Card popCard() {
//        if(isEmpty())
//            throw new NoSuchElementException("Card pile is empty");
        return cards.pop();
    }

    public Card peekTopCard() {
//        if(isEmpty())
//            throw new NoSuchElementException("Card pile is empty");
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

    @Override
    public String toString() {
        return "CardPile{" +
                "cards=" + cards +
                '}';
    }
}
