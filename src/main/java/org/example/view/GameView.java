package org.example.view;

import org.example.controller.GameController;
import org.example.model.Game;
import org.example.model.Player;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class GameView implements Observer {
    private final GameController controller;
    private final Scanner scanner;

    public GameView(GameController controller) {
        scanner = new Scanner(System.in);
        this.controller = controller;
    }

    public void getNumberOfPlayers() {
        System.out.print("- Number of players: ");
        int numberPlayers = Integer.parseInt(scanner.nextLine());
        controller.setNumberOfPlayers();
    }

    public void displayPlayer(Player player) {
        System.out.println(player);
    }

    public void getNumberOfCards() {
        System.out.print("- Number of cards: ");
        int numberCards = Integer.parseInt(scanner.nextLine());
        controller.setNumberOfCards();
    }

    @Override
    public void update(Observable o, Object arg) {
        Game model = (Game) o;

//        when a play is done, this method should update the ui based on the state
//        e.g. the displayed cards that could be played by the player (separated from invalid)
    }
}
