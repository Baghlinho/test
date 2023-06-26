package org.example.ui;

import org.example.game.Player;
import org.example.card.Card;
import org.example.card.Color;

public interface UnoInterface {
    int promptPlayersCount(int dealtCards, int drawPileSize);
    String promptPlayerName(int order);
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
    Color announceColor(Color[] values);
    void forgotToSayUno(int numCards);
}