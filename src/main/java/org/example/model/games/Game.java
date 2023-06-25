package org.example.model.games;

import org.example.model.CardPile;
import org.example.model.Player;
import org.example.model.card.Card;
import org.example.model.card.Color;
import org.example.model.card.CardFactory;
import org.example.model.dealing.DealStrategy;
import org.example.view.ConsoleInterface;
import org.example.view.UnoInterface;

import java.util.Observable;

public abstract class Game extends Observable {
    private final CardFactory cardFactory;
    protected CardPile drawPile, discardPile;
    protected Player[] players;
    protected Player currentPlayer;
    private int turn, direction, cardsEach;
    private boolean isCardPlayed;
    private static final int LEFT_DIRECTION = 1, RIGHT_DIRECTION = -1;
    private final UnoInterface userInterface;
    private final DealStrategy dealStrategy;

    protected Game(int cardsEach) {
        this.cardsEach = cardsEach;
        direction = LEFT_DIRECTION;
        cardFactory = new CardFactory();
        discardPile = new CardPile();
        drawPile = new CardPile();
        userInterface = new ConsoleInterface();
        dealStrategy = getDealStrategy();
    }

    public void play () {
        setUpGame();
        startGame();
    }

    protected final void setUpGame() {
        setUpCardPiles();
        initializePlayers();
        dealCards();
        getLastCardDiscarded().executeEffect(this);
    }

    protected final void startGame() {
        while(!playTurn()) {
            if(!isCardPlayed)
                nextPlayer();
            else isCardPlayed = false;
        }
//        sayUno();
    }

    public boolean playTurn() {
        userInterface.displayTurnInfo(currentPlayer, getLastCardDiscarded());
        String validIndices = currentPlayer.getValidIndices();
        userInterface.displayValidIndices(validIndices);
//        if(validIndices.equals("[]")) {
//            drawCards(1);
//        }
//        validIndices = currentPlayer.getValidIndices();
//        if(validIndices.equals("[]")) {
//            userInterface.displayPlayNotPossible();
//            return;
//        }
//        if(currentPlayer.getHandSize() == 2)
//            sayUno();
        if(currentPlayer.isHandEmpty()){
            userInterface.displayGameWinner(currentPlayer.getName());
            return true;
        }
        discardCard();
        isCardPlayed = true;
        return false;
    }

    private void setUpCardPiles() {
        setUpDrawPile();
        discardPile.pushCard(drawPile.popCard());
    }

//    private void sayUno() {
//        FutureTask<Boolean> sayUno = new FutureTask<>(this::waitUntilSayUno);
//        ExecutorService executor = Executors.newFixedThreadPool(2);
//        executor.execute(sayUno);
//        try {
//            sayUno.get(5000, TimeUnit.MILLISECONDS);
//        } catch (TimeoutException e) {
//            drawCards(4);
//        } catch (ExecutionException | InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private boolean waitUntilSayUno() {
//        String input = userInterface.promptSayUno().toLowerCase().trim();
//        while(!input.equals("uno")) {
//            input = userInterface.chat().toLowerCase().trim();
//        }
//        return true;
//        // IDK how to remove return value
//    }

    protected final void addWildCard(String type, int count) {
        Card card = cardFactory.createWildCard(type);
        addCardToDeck(card, count);
    }

    protected final void addActionCard(String type, Color color, int count) {
        Card card = cardFactory.createActionCard(type, color);
        addCardToDeck(card, count);
    }

    protected final void addNumberCard(Color color, int value, int count) {
        Card card = cardFactory.createNumberCard(color, value);
        addCardToDeck(card, count);
    }

    private void addCardToDeck(Card card, int count) {
        drawPile.pushCard(card);
        for (int i = 0; i < count-1; i++) {
            drawPile.pushCard(card.clone());
        }
    }

    public void initializePlayers() {
        int count = userInterface.promptPlayersCount();
        initializePlayers(count);
    }

    public void initializePlayers(int count) {
        if(count < 2)
            throw new IllegalArgumentException("There should be at least 2 players");
        players = new Player[count];
        String[] names = userInterface.promptPlayerNames(count);
        for (int i = 0; i < count; i++) {
            players[i] = new Player(names[i]);
        }
        currentPlayer = players[0];
    }

    public void dealCards() {
        dealStrategy.deal(this);
    }

    public final void nextPlayer() {
        turn = (turn + direction + players.length) % players.length;
        currentPlayer = players[turn];
    }

    public final void reverseDirection() {
        if(direction == LEFT_DIRECTION)
            direction = RIGHT_DIRECTION;
        else if (direction == RIGHT_DIRECTION)
            direction = LEFT_DIRECTION;
    }

    private Card getLastCardDiscarded() {
        return discardPile.peekTopCard();
    }

    public void drawCards(int count) {
        for (int i = 0; i < count; i++) {
            if(drawPile.isEmpty())
                makeDiscardPileDrawPile();
            Card card = drawPile.popCard();
            currentPlayer.obtainCard(card);
        }
    }

    public void discardCard() {
        int cardIndex = userInterface.promptDiscard();
        Card card = currentPlayer.discardCard(cardIndex);
        discardPile.pushCard(card);
        card.executeEffect(this);
    }

    private void makeDiscardPileDrawPile() {
        discardPile.shuffle();
        drawPile = discardPile;
        discardPile = new CardPile();
    }

    public int getPlayersCount() {
        return players.length;
    }

    public int getCardsEach() {
        return cardsEach;
    }

    protected abstract void setUpDrawPile();

    protected abstract DealStrategy getDealStrategy();

    public boolean isCardPlayed() {
        return isCardPlayed;
    }

    public void setCardPlayed(boolean cardPlayed) {
        isCardPlayed = cardPlayed;
    }
}
