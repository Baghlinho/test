package org.example.model.card;

public class WildCard extends NumberCard {
    public WildCard(CardColor color, int value) {
        super(color, value, CardType.Wild);
    }

    @Override
    public void doActions() {
        actions.switchColor();
    }
}
