package org.example.model.games;

import org.example.model.CardPile;
import org.example.model.Player;
import org.example.model.card.Card;
import org.example.model.card.Color;
import org.example.model.card.CardFactory;
import org.example.model.dealing.DealStrategy;
import org.example.model.rules.MatchColorRule;
import org.example.model.rules.MatchSymbolRule;
import org.example.model.rules.PlayCardRule;
import org.example.model.rules.WildCardRule;
import org.example.view.ConsoleInterface;
import org.example.view.UnoInterface;

import java.util.Observable;

public abstract class Game extends Observable {
    private final CardFactory cardFactory;
    protected CardPile drawPile, discardPile;
    protected Player[] players;
    protected Player currentPlayer;
    private int turn;
    private int direction;
    private final int cardsEach;
    private final int rounds;
    private boolean isCardPlayed;
    private static final int LEFT_DIRECTION = 1, RIGHT_DIRECTION = -1;
    private final UnoInterface userInterface;
    private final DealStrategy dealStrategy;
    private String currentPlayCardRule;
    private PlayCardRule[] rules;

    protected Game(int cardsEach, int rounds) {
        this.cardsEach = cardsEach;
        this.rounds = rounds;
        direction = LEFT_DIRECTION;
        cardFactory = new CardFactory();
        discardPile = new CardPile();
        drawPile = new CardPile();
        userInterface = new ConsoleInterface();
        dealStrategy = getDealStrategy();
        currentPlayCardRule = "";
    }

    public void play () {
        setUpDrawPile();
        initializePlayers();
        currentPlayer = players[0];
        for (int i = 0; i < rounds; i++) {
            for (int j = 0; j < i; j++) {
                nextPlayer();
            }
            userInterface.announceBeginRound(i+1, currentPlayer.getName());
            playRound();
            currentPlayer = players[0];
            resetCardPiles();
        }
        displayGameWinner();
    }

    private void displayGameWinner() {
        Player bestPlayer = players[0];
        int secondBestScore = 0;
        for (int i = 1; i < players.length; i++) {
            if(players[i].getScore() >= bestPlayer.getScore()) {
                secondBestScore = bestPlayer.getScore();
                bestPlayer = players[i];
            }
        }
        if(bestPlayer.getScore() == secondBestScore)
            userInterface.announceDraw();
        userInterface.announceGameWinner(bestPlayer.getName());
    }

    protected final void playRound() {
        dealCards();
        drawPile.transferCard(discardPile);
        getLastCardDiscarded().executeEffect(this);
        rules = getPlayCardRule(currentPlayCardRule);
        while(!playTurn()) {
            if(!isCardPlayed)
                nextPlayer();
            else isCardPlayed = false;
        }
    }

    public boolean playTurn() {
        userInterface.displayTurnInfo(currentPlayer, getLastCardDiscarded());
        currentPlayer.updateValidIndices(rules);
        String validIndices = currentPlayer.getValidIndices();
        if(validIndices.equals("[]")) {
            userInterface.promptDrawCard();
            drawCards(1);
            userInterface.displayDrawnCard(currentPlayer.getCard(currentPlayer.getHandSize()-1));
            currentPlayer.updateValidIndices(rules);
            validIndices = currentPlayer.getValidIndices();
        }
        if(validIndices.equals("[]")) {
            userInterface.displayPlayNotPossible();
            return false;
        }
        userInterface.displayValidIndices(validIndices);
        isCardPlayed = true;
        return discardCard();
    }

    private void sayUno() {
        String input = userInterface.promptSayUno();
        if(!input.equals("uno")) {
            drawCards(2);
        }
    }

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
            if(drawPile.isEmpty()) {
                userInterface.notifyDrawPileEmpty();
                resetCardPiles();
            }
            Card card = drawPile.popCard();
            currentPlayer.obtainCard(card);
        }
    }

    public boolean discardCard() {
        if (currentPlayer.getHandSize() == 1) {
            userInterface.announceRoundWinner(currentPlayer.getName());
            displayPlayerScores();
            Card card = currentPlayer.discardCard(0);
            discardPile.pushCard(card);
            return true;
        }
        else {
            int cardIndex = userInterface.promptDiscard();
            Card card = currentPlayer.discardCard(cardIndex);
            discardPile.pushCard(card);
            if(currentPlayer.getHandSize() == 1)
                sayUno();
            card.executeEffect(this);
            rules = getPlayCardRule(currentPlayCardRule);
            setPlayCardRule("");
            return false;
        }
    }

    private void resetCardPiles() {
        for(Player player: players)
            while(player.getHandSize() != 0)
                drawPile.pushCard(player.removeCard(0));
        while(!discardPile.isEmpty())
            discardPile.transferCard(drawPile);
        drawPile.shuffle();
    }

    public int getPlayersCount() {
        return players.length;
    }

    public int getCardsEach() {
        return cardsEach;
    }

    protected abstract void setUpDrawPile();

    protected abstract DealStrategy getDealStrategy();

    public void setCardPlayed(boolean cardPlayed) {
        isCardPlayed = cardPlayed;
    }

    public void displayPlayerScores() {
        for (int i = (turn+1) % players.length; i != turn; i = (i+1) % players.length) {
            currentPlayer.setScore(currentPlayer.getScore() + players[i].computeTotalValue());
        }
        for(Player player: players)
            userInterface.displayPlayerScore(player.getName(), player.getScore());
    }

    public void setPlayCardRule(String rule) {
        this.currentPlayCardRule = rule;
    }

    protected PlayCardRule[] getDefaultPlayCardRule() {
        Color colorToMatch = getLastCardDiscarded().getColor();
        String symbolToMatch = getLastCardDiscarded().getSymbol();
        PlayCardRule defaultRule = new WildCardRule();
        defaultRule = new MatchSymbolRule(defaultRule, symbolToMatch);
        defaultRule = new MatchColorRule(defaultRule, colorToMatch);
        return new PlayCardRule[]{defaultRule};
    }

    public PlayCardRule[] getPlayCardRule(String rule) {
        if (rule.equals("SelectColor")) {
            Color color = userInterface.promptSelectColor(Color.values());
            return new PlayCardRule[]{new MatchColorRule(new WildCardRule(), color)};
        }
        return getDefaultPlayCardRule();
    }
}
