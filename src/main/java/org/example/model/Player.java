package org.example.model;

import org.example.model.card.Card;

import java.util.ArrayList;

public class Player {
    private final String name;
    private final ArrayList<Card> hand;

    public Player(String name) {
        this.name = name;
        hand = new ArrayList<>();
    }

    public void obtainCard(Card card) {
        if(card == null)
            throw new IllegalArgumentException("Card can't be null");
        hand.add(card);
    }

    public Card discardCard(Card card) {
        boolean isCardExist = hand.remove(card);
        if (!isCardExist)
            throw new IllegalArgumentException("Card does not exist");
        return card;
    }

    public String getName() {
        return name;
    }
}
