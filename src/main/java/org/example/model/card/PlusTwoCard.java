package org.example.model.card;

public class PlusTwoCard extends NumberCard {
    public PlusTwoCard(CardColor color) {
        super(color, 20, CardType.Action);
    }

    public void doActions() {
        actions.draw(2);
    }
}
