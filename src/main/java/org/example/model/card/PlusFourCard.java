package org.example.model.card;

public class PlusFourCard extends NumberCard {
    public PlusFourCard() {
        super(CardColor.Wild, 20, CardType.Wild);
    }

    @Override
    public void doActions() {
        actions.switchColor();
        actions.draw(4);
        actions.skip();
    }
}
