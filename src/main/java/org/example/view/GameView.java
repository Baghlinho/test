package org.example.view;

import org.example.controller.GameController;
import org.example.model.games.Game;
import org.example.model.Player;

import java.util.Observable;

public class GameView extends View {

    public GameView(GameController controller) {
        super(controller);
    }

    private void promptPlayersSettings() {
        System.out.print("Number of players: ");
        int playersCount = Integer.parseInt(scanner.nextLine());
        String[] names = new String[playersCount];
        for (int i = 0; i < playersCount; i++) {
            System.out.printf("Player %d name: ", i+1);
            names[i] = scanner.nextLine();
        }
        controller.initializePlayers(playersCount, names);
    }

    @Override
    public void update(Observable o, Object arg) {
//        Game game = (Game) o;
        promptPlayersSettings();
//        when a play is done, this method should update the ui based on the state
//        e.g. the displayed cards that could be played by the player (separated from invalid)
    }
}
