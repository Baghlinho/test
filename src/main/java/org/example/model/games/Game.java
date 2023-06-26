package org.example.model.games;

import org.example.model.CardPile;
import org.example.model.Player;
import org.example.model.card.Card;
import org.example.model.card.Color;
import org.example.model.dealing.DealStrategy;
import org.example.model.gens.MatchColorOnly;
import org.example.model.gens.PredefinedDeck;
import org.example.model.rules.PlayCardRule;
import org.example.view.UnoInterface;

public abstract class Game {

    private final int dealtCards, rounds, points;
    private final int drawToPlayAmount, missedUnoDrawAmount;
    private final PredefinedDeck deck;
    private final UnoInterface unoInterface;
    private final DealStrategy dealStrategy;
    private PlayDirection playDirection;
    protected CardPile drawPile, discardPile;
    protected Player[] players;
    protected Player currentPlayer;
    private int turn;
    private PlayCardRule[] rules;

    protected Game(int dealtCards, int rounds, int points,
                   int drawToPlayAmount, int missedUnoDrawAmount,
                   PredefinedDeck deck, UnoInterface unoInterface,
                   DealStrategy dealStrategy, PlayDirection playDirection) {
        this.dealtCards = dealtCards;
        this.rounds = rounds;
        this.points = points;
        this.drawToPlayAmount = drawToPlayAmount;
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
        if(dealtCards<1)
            throw new IllegalArgumentException("At least one card should be dealt to each player");
        if(drawToPlayAmount < 1)
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
                nextPlayer();
            unoInterface.announceBeginRound(i+1, currentPlayer.getName());
            playRound();
            if(isPointLimitReached())
                break;
        }
        displayGameWinner();
    }

    private boolean isPointLimitReached() {
        for(Player player: players)
            if(player.getScore() >= points)
                return true;
        return false;
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
            unoInterface.announceDraw();
        unoInterface.announceGameWinner(bestPlayer.getName());
    }

    private void playRound() {
        dealCards();
        drawPile.transferCard(discardPile);
        rules = getDefaultPlayCardRule();
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
            drawToPlayPenalty();
            currentPlayer.updateValidIndices(rules);
            validIndices = currentPlayer.getValidIndices();
        }
        if(validIndices.equals("[]")) {
            unoInterface.displayPlayNotPossible();
            nextPlayer();
            return true;
        }
        unoInterface.displayValidIndices(validIndices);
        Card card = discardCard();
        rules = getDefaultPlayCardRule();
        if(haveToSayUno())
            if(!sayUno())
                missedUnoPenalty();
        if(currentPlayer.getHandSize() == 0)
            return false;
        card.executeEffect(this);
        return true;
    }

    private void drawToPlayPenalty() {
        unoInterface.promptNoPlayDrawPen(drawToPlayAmount);
        drawCards(drawToPlayAmount);
        Card[] cards = new Card[drawToPlayAmount];
        for(int i = drawToPlayAmount; i >= 1; i--)
            cards[i-1] = currentPlayer.getCard(currentPlayer.getHandSize() - i);
        unoInterface.displayDrawnCards(cards);
    }

    private boolean sayUno() {
        String input = unoInterface.promptSayUno();
        return input.equals("uno");
    }

    private void missedUnoPenalty() {
        drawCards(missedUnoDrawAmount);
        unoInterface.forgotToSayUno(missedUnoDrawAmount);
    }

    private void initializePlayers() {
        int count = unoInterface.promptPlayersCount();
        initializePlayers(count);
    }

    private void initializePlayers(int count) {
        if(count < 2)
            throw new IllegalArgumentException("There should be at least 2 players");
        players = new Player[count];
        String[] names = unoInterface.promptPlayerNames(count);
        for (int i = 0; i < count; i++) {
            players[i] = new Player(names[i]);
        }
    }

    private void dealCards() {
        dealStrategy.deal(this);
    }

    public final void nextPlayer() {
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
        int cardIndex = unoInterface.promptDiscard();
        Card card = currentPlayer.discardCard(cardIndex);
        discardPile.pushCard(card);
        return card;
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

    public int getDealtCards() {
        return dealtCards;
    }

    public void displayPlayerScores() {
        for (int i = (turn+1) % players.length; i != turn; i = (i+1) % players.length) {
            currentPlayer.setScore(currentPlayer.getScore() + players[i].computeHandValue());
        }
        for(Player player: players)
            unoInterface.displayPlayerScore(player.getName(), player.getScore());
    }

    protected abstract PlayCardRule[] getDefaultPlayCardRule();

    public void selectPlayColor() {
        Color color = unoInterface.promptSelectColor(Color.values());
        rules = new MatchColorOnly(color).generateRuleSet();
    }
}
