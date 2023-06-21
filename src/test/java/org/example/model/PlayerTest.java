package org.example.model;

import org.example.model.card.Card;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void obtainCard() {
        Card card = new Card();
        Player player = new Player("Firas");
        player.obtainCard(card);
//        player.obtainCard(null);
    }

    @Test
    void discardCard() {
    }
}