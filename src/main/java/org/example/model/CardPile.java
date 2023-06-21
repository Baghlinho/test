package org.example.model;

import org.example.model.card.Card;

import java.util.Collections;
import java.util.EmptyStackException;
import java.util.Stack;

public class CardPile {
    private final Stack<Card> cards = new Stack<>();

    public void pushCard(Card card) {
        if(card == null)
            throw new IllegalArgumentException("Card can't be null");
        cards.push(card);
    }

    public Card popCard() {
        try {
            Card card = cards.pop();
        }
        catch (EmptyStackException ex) {
            System.out.println("STACK EMPTY");
            // throw EmptyCardPileException();
        }
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
}
