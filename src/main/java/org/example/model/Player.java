package org.example.model;

import org.example.model.card.Card;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Player {
    private final String name;
    private final static Set<String> names = new HashSet<>();
    private final ArrayList<Card> hand;

    public Player(String name) {
        if(names.contains(name)) {
            throw new IllegalArgumentException("Player name is already taken");
        }
        names.add(name);
        this.name = name;
        hand = new ArrayList<>();
    }

    public void obtainCard(Card card) {
        if(card == null)
            throw new IllegalArgumentException("Card can't be null");
        hand.add(card);
    }

    public Card discardCard(int index) {
        if(!checkValidIndices().contains(index))
            throw new IllegalArgumentException("Invalid card index");
        return discardCard(hand.get(index));
    }

    private Card discardCard(Card card) {
        boolean isCardExist = hand.remove(card);
        if (!isCardExist)
            throw new IllegalArgumentException("Card does not exist in hand");
        return card;
    }

    public String getName() {
        return name;
    }

    public String getHand() {
        return hand.toString();
    }

    public boolean isHandEmpty() {
        return hand.isEmpty();
    }

    public int getHandSize() {
        return hand.size();
    }

    public String getValidIndices() {
        return checkValidIndices().toString();
    }

    private ArrayList<Integer> checkValidIndices() {
        ArrayList<Integer> validOptions = new ArrayList<>();
        for (int i = 0; i < getHandSize(); i++) {
            if(isCardValid(hand.get(i)))
                validOptions.add(i);
        }
        return validOptions;
    }

    private boolean isCardValid(Card card) {
//        ruleChecker(card);
        return true;
    }

}
