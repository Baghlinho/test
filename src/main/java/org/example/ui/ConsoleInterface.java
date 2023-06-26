package org.example.ui;

import org.example.game.Player;
import org.example.card.Card;
import org.example.card.Color;

import java.util.Arrays;
import java.util.Scanner;

public class ConsoleInterface implements UnoInterface{

    protected final Scanner scanner;

    public ConsoleInterface() {
        scanner = new Scanner(System.in);
    }

    @Override
    public int promptPlayersCount(int dealtCards, int drawPileSize) {
        System.out.print("- Number of players: ");
        while(true) {
            try {
                int count = Integer.parseInt(scanner.nextLine().trim());
                if(count < 2) {
                    System.out.print("--> There should be at least two players, try again: ");
                    continue;
                }
                int threshold = drawPileSize / dealtCards - 1;
                if(count > threshold) {
                    System.out.print("--> There are not enough cards, try choosing a number below " + (threshold+1) + ": ");
                    continue;
                }
                return count;
            }
            catch (NumberFormatException e) {
                System.out.print("--> Number invalid, try again: ");
            }
        }
    }

    @Override
    public String promptPlayerName(int order) {
        System.out.printf("- Player %d name: ", order);
        return scanner.nextLine().trim();
    }

    @Override
    public void displayTurnInfo(Player player, Card lastDiscardCard, int drawPileSize) {
        printBarrier();
        System.out.println("- Turn: " + player.getName());
        System.out.println("- Hand: " + player.getHand());
        System.out.println("- Size of draw pile: " + drawPileSize);
        System.out.println("- Discard pile card: " + lastDiscardCard);
    }

    @Override
    public int promptDiscard () {
        while(true) {
            try {
                System.out.print("- Enter index of card to discard: ");
                return Integer.parseInt(scanner.nextLine().trim());
            }
            catch (NumberFormatException e) {
                System.out.println("--> Number invalid, try again ");
            }
        }
    }

    @Override
    public void promptNoPlayDrawPen(int count) {
        System.out.print("- You can't play any card, you have to draw "+ count +" card(s) by pressing Enter: ");
        scanner.nextLine();
    }

    @Override
    public void displayDrawnCards(Card[] cards) {
        System.out.println("- Drawn cards: " + Arrays.toString(cards));
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
        printBarrier();
        System.out.println("Round ended, " + playerName + " won the round!");
    }

    @Override
    public void announceGameWinner(String playerName) {
        printBarrier();
        System.out.println("Game ended, " + playerName + " won the game!");
        printBarrier();
    }

    @Override
    public void announceDraw() {
        printBarrier();
        System.out.println("Game ended in a draw!");
        printBarrier();
    }

    @Override
    public void announceBeginRound(int round, String name) {
        printBarrier();
        System.out.printf("Round %d: %s starts by discarding the first non-wild card from the draw pile\n", round, name);
    }

    @Override
    public void displayPlayerScore(String name, int score) {
        System.out.println("- " + name + ": " + score);
    }

    @Override
    public Color announceColor(Color[] values) {
        System.out.println("- Select the number of the color to play for the next turn:");
        int i = 0;
        for(Color color: values) {
            if (color == Color.WILD) continue;
            System.out.printf("%d. %s\n", i+1, values[i].name());
            i++;
        }
        while(true) {
            try {
                int choice = Integer.parseInt(chat().trim());
                if(choice<1 || choice >= values.length) {
                    System.out.print("--> Choice must be between 1 and " + values.length + ", try again: ");
                    continue;
                }
                return values[choice-1];
            }
            catch (NumberFormatException e) {
                System.out.println("--> Number invalid, try again ");
            }
        }
    }

    @Override
    public void forgotToSayUno(int numCards) {
        System.out.printf("- You didn't say uno! your penalty is drawing %d cards\n", numCards);
    }

    @Override
    public void notifyDrawPileEmpty() {
        try {
            printBarrier();
            System.out.println("Draw pile is empty. Reorganizing piles...");
            Thread.sleep(2000);
            printBarrier();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printBarrier() {
        System.out.println("*************************************************************************************************");
    }
}