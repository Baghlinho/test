package org.example.view;

import org.example.model.Player;
import org.example.model.card.Card;
import org.example.model.card.Color;

import java.util.Scanner;

public class ConsoleInterface implements UnoInterface{

    protected final Scanner scanner;

    public ConsoleInterface() {
        scanner = new Scanner(System.in);
    }

    @Override
    public int promptPlayersCount() {
        System.out.print("Number of players: ");
        return Integer.parseInt(scanner.nextLine());
    }

    @Override
    public String[] promptPlayerNames(int playersCount) {
        String[] names = new String[playersCount];
//        System.out.print("(Dealer) ");
        for (int i = 0; i < playersCount; i++) {
            System.out.printf("Player %d name: ", i+1);
            names[i] = scanner.nextLine();
        }
        return names;
    }

    @Override
    public void displayTurnInfo(Player player, Card lastDiscardCard) {
        // split into display name and display hand and display discard pile
        System.out.println("*****************************");
        System.out.println("- Turn: " + player.getName());
        System.out.println("- Hand: " + player.getHand());
        System.out.println("- Discard pile card: " + lastDiscardCard);
    }

    @Override
    public int promptDiscard () {
        System.out.print("- Enter index of card to discard: ");
        return Integer.parseInt(scanner.nextLine());
    }

    @Override
    public void promptDrawCard() {
        System.out.print("- You can't play any card, you have to draw a card by pressing Enter: ");
        scanner.nextLine();
    }

    @Override
    public void displayDrawnCard(Card card) {
        System.out.println("- Drawn card: " + card);
    }

    @Override
    public void displayPlayNotPossible() {
        System.out.println("- You have no cards to play, your turn is skipped");
    }

    @Override
    public void displayValidIndices(String validIndices) {
        System.out.println("- Valid indices: " + validIndices);
    }

    @Override
    public String promptSayUno() {
        System.out.println("!!! YOU ONLY HAVE ONE CARD LEFT, SAY THE MAGIC WORD !!!");
        return chat().toLowerCase().trim();
    }

    @Override
    public String chat() {
        System.out.print("(chat)--> ");
        return scanner.nextLine();
    }

    @Override
    public void announceRoundWinner(String playerName) {
        System.out.print("- Discard the last card by pressing Enter: ");
        scanner.nextLine();
        System.out.println("*****************************");
        System.out.println("Round ended, " + playerName + " won the round!");
    }

    @Override
    public void announceGameWinner(String playerName) {
        System.out.println("*****************************");
        System.out.println("Game ended, " + playerName + " won the game!");
        System.out.println("*****************************");
    }

    @Override
    public void announceDraw() {
        System.out.println("*****************************");
        System.out.println("Game ended in a draw!");
        System.out.println("*****************************");
    }

    @Override
    public void announceBeginRound(int round, String name) {
        System.out.println("*****************************");
        System.out.printf("Round %d: %s starts by discarding the first card from the draw pile\n", round, name);
    }

    @Override
    public void displayPlayerScore(String name, int score) {
        System.out.println("- " + name + ": " + score);
    }

    @Override
    public Color promptSelectColor(Color[] values) {
        System.out.println("- Select the number of the color to play for the next turn:");
        int i = 0;
        for(Color color: values) {
            if (color == Color.WILD) continue;
            System.out.printf("%d. %s\n", i+1, values[i].name());
            i++;
        }
        int choice = Integer.parseInt(chat());
        // throw exception
        return values[choice-1];
    }

    @Override
    public void notifyDrawPileEmpty() {
        try {
            System.out.println("*****************************");
            System.out.println("Draw pile is empty. Reorganizing piles...");
            Thread.sleep(2000);
            System.out.println("*****************************");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
