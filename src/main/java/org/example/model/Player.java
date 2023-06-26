package org.example.model;

import org.example.model.card.Card;
import org.example.model.rules.PlayCardRule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Player {
    private final String name;
    private final static Set<String> names = new HashSet<>();
    private final ArrayList<Card> hand;
    private final ArrayList<Integer> validIndices;
    private int score;

    public Player(String name) {
        if(names.contains(name)) {
            throw new IllegalArgumentException("Player name is already taken");
        }
        names.add(name);
        this.name = name;
        hand = new ArrayList<>();
        validIndices = new ArrayList<>();
    }

    public void obtainCard(Card card) {
        if(card == null)
            throw new IllegalArgumentException("Card can't be null");
        hand.add(card);
    }

    public Card getCard(int index) {
        return hand.get(index);
    }

    public Card discardCard(int index) {
        if(!validIndices.contains(index))
            throw new IllegalArgumentException("Invalid card index");
        return discardCard(getCard(index));
    }

    private Card discardCard(Card card) {
        boolean isCardExist = hand.remove(card);
        if (!isCardExist)
            throw new IllegalArgumentException("Card does not exist in hand");
        return card;
    }

    public Card removeCard(int index) {
        // throw exception // replace by better thing
        return hand.remove(index);
    }

    public String getName() {
        return name;
    }

    public String getHand() {
        return hand.toString();
    }

    public int getHandSize() {
        return hand.size();
    }

    public String getValidIndices() {
        return validIndices.toString();
    }

    public void updateValidIndices(PlayCardRule[] rules) {
        validIndices.clear();
        for (int i = 0; i < getHandSize(); i++) {
            if(isCardValid(getCard(i), rules))
                validIndices.add(i);
        }
    }

    public boolean isCardValid(Card card, PlayCardRule[] rules) {
        boolean isValid = true;
        for(PlayCardRule rule: rules)
            isValid = isValid && rule.check(card);
        return isValid;
    }

    public int computeTotalValue() {
        int total = 0;
        for(Card card: hand)
            total += card.getValue();
        return total;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
