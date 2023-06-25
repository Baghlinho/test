package org.example.view;

import org.example.model.Player;
import org.example.model.card.Card;

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
//        CHANGE PROMPT OF FIRST PLAYER TO BE DEALER??? TURN BEGINS LEFT TO DEALER
        for (int i = 0; i < playersCount; i++) {
//            USE STATIC VARIABLE TO COUNT PLAYERS???
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
    public void displayPlayNotPossible() {
        System.out.println("You have no cards to play, ");
    }

    @Override
    public void displayValidIndices(String validIndices) {
        System.out.println("- Valid indices: " + validIndices);
    }

    @Override
    public String promptSayUno() {
        System.out.println("!!! YOU ONLY HAVE ONE CARD LEFT, SAY THE MAGIC WORD !!!");
        return chat();
    }

    @Override
    public String chat() {
        System.out.print("(chat)--> ");
        return scanner.nextLine();
    }

    @Override
    public void displayGameWinner(String playerName) {
        System.out.println("*****************************");
        System.out.println("Game ended! " + playerName + " won the game");
    }
}
