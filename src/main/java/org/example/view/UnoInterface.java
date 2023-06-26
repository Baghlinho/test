package org.example.view;

import org.example.model.Player;
import org.example.model.card.Card;
import org.example.model.card.Color;

public interface UnoInterface {
    // SO MANY METHODS, VIOLATES INTERFACE SEGREGATION PRINCIPLE
    int promptPlayersCount();
    String[] promptPlayerNames(int playersCount);
    int promptDiscard();
    String promptSayUno();
    void announceRoundWinner(String playerName);
    void displayTurnInfo(Player player, Card lastDiscardCard, int drawPileSize);
    void displayPlayNotPossible();
    void displayValidIndices(String validIndices);
    String chat();
    void announceGameWinner(String playerName);
    void announceDraw();
    void displayPlayerScore(String name, int score);
    void promptNoPlayDrawPen(int count);
    void displayDrawnCards(Card[] cards);
    void notifyDrawPileEmpty();
    void announceBeginRound(int i, String name);
    Color promptSelectColor(Color[] values);
    void forgotToSayUno(int numCards);
}