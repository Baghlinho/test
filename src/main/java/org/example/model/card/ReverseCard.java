package org.example.model.card;

public class ReverseCard extends NumberCard {
    public ReverseCard(CardColor color) {
        super(color, 20, CardType.Action);
    }

    @Override
    public void doActions() {
        actions.reverse();
    }
}
