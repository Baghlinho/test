package org.example.view;

import org.example.model.Player;
import org.example.model.card.Card;

public interface UnoInterface {
    int promptPlayersCount();
    String[] promptPlayerNames(int playersCount);
    int promptDiscard();

    String promptSayUno();

    void displayGameWinner(String playerName);

    void displayTurnInfo(Player player, Card lastDiscardCard);

    void displayPlayNotPossible();

    void displayValidIndices(String validIndices);

    String chat();
}
