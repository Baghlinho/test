package org.example.view;

import org.example.model.Player;
import org.example.model.card.Card;
import org.example.model.card.Color;

public interface UnoInterface {
    int promptPlayersCount();
    String[] promptPlayerNames(int playersCount);
    int promptDiscard();

    String promptSayUno();

    void announceRoundWinner(String playerName);

    void displayTurnInfo(Player player, Card lastDiscardCard);

    void displayPlayNotPossible();

    void displayValidIndices(String validIndices);

    String chat();

    void announceGameWinner(String playerName);

    void announceDraw();

    void displayPlayerScore(String name, int score);

    void promptDrawCard();

    void displayDrawnCard(Card card);

    void notifyDrawPileEmpty();

    void announceBeginRound(int i, String name);

    Color promptSelectColor(Color[] values);
}
