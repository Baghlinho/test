package org.example.game;

import org.example.card.Card;
import org.example.card.CardPile;
import org.example.card.Color;
import org.example.gens.MatchColorOnly;
import org.example.gens.PredefinedDeckGenerator;
import org.example.dealing.DealStrategy;
import org.example.rules.PlayCardRule;
import org.example.ui.UnoInterface;

public abstract class Game {
    private final int eachDealtCount, rounds, points;
    private final int noLegalPlayAmount, missedUnoDrawAmount;
    private final PredefinedDeckGenerator deck;
    private final UnoInterface unoInterface;
    private final DealStrategy dealStrategy;
    private PlayDirection playDirection;
    protected CardPile drawPile, discardPile;
    protected Player[] players;
    protected Player currentPlayer;
    private int turn;
    private PlayCardRule[] rules;

    protected Game(int eachDealtCount, int rounds, int points,
                   int noLegalPlayAmount, int missedUnoDrawAmount,
                   PredefinedDeckGenerator deck, UnoInterface unoInterface,
                   DealStrategy dealStrategy, PlayDirection playDirection) {
        this.eachDealtCount = eachDealtCount;
        this.rounds = rounds;
        this.points = points;
        this.noLegalPlayAmount = noLegalPlayAmount;
        this.missedUnoDrawAmount = missedUnoDrawAmount;
        this.deck = deck;
        this.playDirection = playDirection;
        this.unoInterface = unoInterface;
        this.dealStrategy = dealStrategy;
        checkGameValidity();
    }

    private void checkGameValidity() {
        if(rounds < 1)
            throw new IllegalArgumentException("At least one round should be played");
        if(eachDealtCount <1)
            throw new IllegalArgumentException("At least one card should be dealt to each player");
        if(noLegalPlayAmount < 1)
            throw new IllegalArgumentException("No play penalty should be at least drawing one card");
        if(missedUnoDrawAmount < 0)
            throw new IllegalArgumentException("Draw penalty amount can't be negative");
        if(deck == null || playDirection == null || unoInterface == null || dealStrategy == null)
            throw new NullPointerException("Reference variables shouldn't be null");
    }

    public void play () {
        generateDeck();
        initializePlayers();
        for (int i = 0; i < rounds; i++) {
            resetDeck();
            currentPlayer = players[0];
            for (int j = 0; j < i; j++)
                proceedNextPlayer();
            unoInterface.announceBeginRound(i+1, currentPlayer.getName());
            playRound();
            if(isPointLimitReached())
                break;
        }
        determineGameWinner();
    }

    private boolean isPointLimitReached() {
        for(Player player: players)
            if(player.getScore() >= points)
                return true;
        return false;
    }

    private void determineGameWinner() {
        Player bestPlayer = players[0];
        int secondBestScore = 0;
        for (int i = 1; i < players.length; i++) {
            if(players[i].getScore() >= bestPlayer.getScore()) {
                secondBestScore = bestPlayer.getScore();
                bestPlayer = players[i];
            }
        }
        if(bestPlayer.getScore() == secondBestScore)
            unoInterface.announceDraw();
        unoInterface.announceGameWinner(bestPlayer.getName());
    }

    private void playRound() {
        dealCards();
        drawPile.transferCard(discardPile);
        unoInterface.displayTurnInfo(currentPlayer, getLastCardDiscarded(), drawPile.size());
        while(getLastCardDiscarded().getColor() == Color.WILD)
            drawPile.transferCard(discardPile);
        getLastCardDiscarded().executeEffect(this);
        while(true) {
            if(!playTurn())
                break;
        }
        unoInterface.announceRoundWinner(currentPlayer.getName());
        displayPlayerScores();
    }

    private boolean playTurn() {
        unoInterface.displayTurnInfo(currentPlayer, getLastCardDiscarded(), drawPile.size());
        currentPlayer.updateValidIndices(rules);
        String validIndices = currentPlayer.getValidIndices();
        if(validIndices.equals("[]")) {
            penalizeNoLegalPlay();
            currentPlayer.updateValidIndices(rules);
            validIndices = currentPlayer.getValidIndices();
        }
        if(validIndices.equals("[]")) {
            unoInterface.displayPlayNotPossible();
            proceedNextPlayer();
            return true;
        }
        unoInterface.displayValidIndices(validIndices);
        Card card = discardCard();
        if(haveToSayUno())
            if(!sayUno())
                penalizeMissedUno();
        if(currentPlayer.getHandSize() == 0)
            return false;
        card.executeEffect(this);
        return true;
    }

    private void penalizeNoLegalPlay() {
        unoInterface.promptNoPlayDrawPen(noLegalPlayAmount);
        drawCards(noLegalPlayAmount);
        Card[] cards = new Card[noLegalPlayAmount];
        for(int i = noLegalPlayAmount; i >= 1; i--)
            cards[i-1] = currentPlayer.getCard(currentPlayer.getHandSize() - i);
        unoInterface.displayDrawnCards(cards);
    }

    private boolean sayUno() {
        String input = unoInterface.promptSayUno();
        return input.equals("uno");
    }

    private void penalizeMissedUno() {
        drawCards(missedUnoDrawAmount);
        unoInterface.forgotToSayUno(missedUnoDrawAmount);
    }

    private void initializePlayers() {
        int count = unoInterface.promptPlayersCount(eachDealtCount, drawPile.size());
        players = new Player[count];
        for (int i = 0; i < count; i++) {
            String name = unoInterface.promptPlayerName(i+1);
            players[i] = new Player(name);
        }
    }

    private void dealCards() {
        dealStrategy.deal(this);
    }

    public final void proceedNextPlayer() {
        turn = (turn + playDirection.getValue()+ players.length) % players.length;
        currentPlayer = players[turn];
    }

    public final void reverseDirection() {
        if(playDirection == PlayDirection.LEFT)
            playDirection = PlayDirection.RIGHT;
        else if (playDirection == PlayDirection.RIGHT)
            playDirection = PlayDirection.LEFT;
    }

    public final Card getLastCardDiscarded() {
        return discardPile.peekTopCard();
    }

    public final void drawCards(int count) {
        for (int i = 0; i < count; i++) {
            if(drawPile.isEmpty()) {
                unoInterface.notifyDrawPileEmpty();
                reFillDrawPile();
            }
            Card card = drawPile.popCard();
            currentPlayer.obtainCard(card);
        }
    }

    protected boolean haveToSayUno() {
        return currentPlayer.getHandSize() == 1;
    }

    public final Card discardCard() {
        while(true) {
            int cardIndex = unoInterface.promptDiscard();
            try {
                Card card = currentPlayer.discardCard(cardIndex);
                discardPile.pushCard(card);
                return card;
            }
            catch (IllegalArgumentException e) {
                System.out.println("--> " + e.getMessage() + ", try again");
            }
        }
    }

    private void generateDeck() {
        drawPile = deck.generateDeck();
        discardPile = new CardPile();
    }

    private void resetDeck() {
        returnPlayerHandsToDeck();
        shufflePilesIntoDraw();
    }
    private void returnPlayerHandsToDeck() {
        for(Player player: players)
            while(player.getHandSize() != 0)
                drawPile.pushCard(player.removeCard(0));
    }

    private void shufflePilesIntoDraw() {
        while(!discardPile.isEmpty())
            discardPile.transferCard(drawPile);
        drawPile.shuffle();
    }

    private void reFillDrawPile() {
        Card discardCard = discardPile.popCard();
        shufflePilesIntoDraw();
        discardPile.pushCard(discardCard);
    }

    public int getPlayersCount() {
        return players.length;
    }

    public int getEachDealtCount() {
        return eachDealtCount;
    }

    private void displayPlayerScores() {
        for (int i = (turn+1) % players.length; i != turn; i = (i+1) % players.length) {
            currentPlayer.setScore(currentPlayer.getScore() + players[i].computeHandValue());
        }
        for(Player player: players)
            unoInterface.displayPlayerScore(player.getName(), player.getScore());
    }

    protected abstract PlayCardRule[] getDefaultPlayCardRule();

    public void selectPlayColor() {
        Color color = unoInterface.announceColor(Color.values());
        rules = new MatchColorOnly(color).generateRuleSet();
    }

    public void resetPlayRule() {
        rules = getDefaultPlayCardRule();
    }
}
