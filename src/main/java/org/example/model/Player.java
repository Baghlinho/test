package org.example.model;

import org.example.model.card.Card;

import java.util.ArrayList;

public class Player {
    private final String name;
    private final ArrayList<Card> hand;
    private CardPile discardPile;
    private CardPile drawPile;

    public Player(String name, CardPile discardPile, CardPile drawPile) {
        this.name = name;
        this.discardPile = discardPile;
        this.drawPile = drawPile;
        hand = new ArrayList<>();
    }

    public void drawCard() {
        Card card = drawPile.popCard();
        hand.add(card);
        if(drawPile.isEmpty()) {
            discardPile.shuffle();
            drawPile = discardPile;
            discardPile = new CardPile();
        }
    }

    public void discardCard(Card card) {
        boolean isCardExist = hand.remove(card);
        if (!isCardExist)
            throw new IllegalArgumentException("Card does not exist");
        discardPile.pushCard(card);
    }

    public String getName() {
        return name;
    }
}
