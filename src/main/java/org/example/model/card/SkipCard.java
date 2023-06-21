package org.example.model.card;

public class SkipCard extends NumberCard {
    public SkipCard(CardColor color) {
        super(color, 20, CardType.Action);
    }

    @Override
    public void doActions() {
        actions.skip();
    }
}
