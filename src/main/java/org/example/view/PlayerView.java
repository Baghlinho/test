package org.example.view;

import org.example.controller.GameController;
import org.example.model.Player;

import java.util.Observable;

public class PlayerView extends View {

    public PlayerView(GameController controller) {
        super(controller);
    }

    @Override
    public void update(Observable o, Object arg) {
        Player player = (Player) arg;
        display(player);
        promptDiscard(player);
    }

    public void display(Player player) {
        System.out.println("*****************************");
        System.out.println("- Turn: " + player.getName());
        System.out.println("- Hand: " + player.getHand());
    }

    public void promptDiscard(Player player) {
        System.out.println("- Enter card to discard: ");
        String cardSymbol = scanner.nextLine();
        controller.discardCard(cardSymbol, player);
    }
}
