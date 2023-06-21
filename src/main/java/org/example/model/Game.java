package org.example.model;

import org.example.model.card.Card;

import java.util.Observable;
import java.util.Scanner;

public class Game extends Observable {
    private final Scanner scanner = new Scanner(System.in);
    private CardPile drawPile, discardPile;
    private Player[] players;
    private int cardsCount;
    private int turn;
    private boolean isDone;
    public void play () {
    initializePiles();
    initializePlayers();
        while (true){
            for (Player player: players) {
                player.obtainCard(drawPile.popCard());
                if(drawPile.isEmpty())
                    reOrganizeCards();
                if(drawPile.isEmpty()){
                    System.out.println("draw pile empty");
                    break;
                }
            }
            break;
        }
//        dealCards();
//        playFromDiscardPile();
//        while(!isWin){
//            playTurn();
//            nextTurn();
//        }
    }

    private void initializePiles() {
        System.out.print("Number of cards: ");
        int cardsCount = Integer.parseInt(scanner.nextLine());
        discardPile = new CardPile();
        drawPile = new CardPile();
    }

    private void initializePlayers() {
        System.out.print("Number of players: ");
        int playersCount = Integer.parseInt(scanner.nextLine());
        players = new Player[playersCount];
        for (int i = 0; i < playersCount; i++) {
            System.out.printf("Player %d name: ", i+1);
            String playerName = scanner.nextLine();
            players[i] = new Player(playerName);
        }
    }

//    public void nextTurn() {
//        turn = (turn + 1) % players.length;
//        incrementBehavior;
//        decrementBehavior;
//    }

    private void addCardToDeck(Enum CardType) {
        // create card using factory creation method
        // add the card to the deck
    }

    protected void addCards(Enum CardType, int count) {
        for (int i = 0; i < count; i++) {
            addCardToDeck(CardType);
        }
    }

    private Card getLastCardDiscarded() {
        return discardPile.peekTopCard();
    }

    private void drawCard(Player player) {
        Card card = drawPile.popCard();
        player.obtainCard(card);
    }

    private void discardCard(Player player) {
//        promptPlayer
        Card card = new Card();
        player.discardCard(card);
        discardPile.pushCard(card);
    }

    private void reOrganizeCards() {
        discardPile.shuffle();
        drawPile = discardPile;
        discardPile = new CardPile();
    }
}
