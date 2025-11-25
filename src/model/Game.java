package model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final List<Player> players;
    private final Deck deck;
    private int currentPlayerIndex;
    private CardSuit requiredSuit;
    private final List<Card> cardsOnTable;

    // Observer pattern
    private final List<GameObserver> observers;

    public Game(List<Player> players) {
        this.players = players;
        this.deck = new Deck();
        this.currentPlayerIndex = 0;
        this.requiredSuit = null;
        this.cardsOnTable = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    // --- OBSERVER METHODS ---

    public void addObserver(GameObserver obs) {
        observers.add(obs);
    }

    public void removeObserver(GameObserver obs) {
        observers.remove(obs);
    }

    private void notifyObservers(GameEvent event) {
        for (GameObserver obs : observers) {
            obs.onGameEvent(event);
        }
    }


    // --- GAME LOGIC ---

    public void startGame() {
        deck.shuffle();
        dealCards();
        notifyObservers(new GameEvent(GameEvent.EventType.GAME_STARTED, null));
    }

    private void dealCards() {
        for (int i = 0; i < 10; i++) {
            for (Player p : players) {
                p.receiveCard(deck.drawCard());
            }
        }
    }

    public void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        notifyObservers(new GameEvent(GameEvent.EventType.NEW_TURN, getCurrentPlayer()));
    }

    public void playCard(Player player, Card card) {

        if (requiredSuit == null) {
            requiredSuit = card.getSuit();
        }

        player.playCard(card);
        cardsOnTable.add(card);

        notifyObservers(new GameEvent(GameEvent.EventType.CARD_PLAYED, card));

        if (isTrickComplete()) {
            notifyObservers(new GameEvent(GameEvent.EventType.TRICK_COMPLETED, new ArrayList<>(cardsOnTable)));
            cardsOnTable.clear();
            requiredSuit = null; 
        }
    }

    public boolean isTrickComplete() {
        return cardsOnTable.size() == players.size();
    }
    
    /**
     * Controlla se la giocata è valida secondo la regola "seguire il seme se possibile".
     */
    public boolean isValidPlay(Player player, Card cardToPlay) {
        if (requiredSuit == null) {
            // primo giocatore del giro può giocare qualsiasi carta
            return true;
        }
        // se la carta giocata è del seme richiesto è valida
        if (cardToPlay.getSuit() == requiredSuit) {
            return true;
        }
        // altrimenti: verifica se il giocatore ha almeno una carta del seme richiesto
        for (Card c : player.getHand()) {
            if (c.getSuit() == requiredSuit) {
                // ha il seme richiesto ma sta giocando altro -> non valido
                return false;
            }
        }
        // non ha il seme richiesto -> può scartare qualsiasi carta
        return true;
    }
    
 // --- GETTERS AND SETTERS ---
    
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public CardSuit getRequiredSuit() {
        return requiredSuit;
    }

    public void setRequiredSuit(CardSuit requiredSuit) {
        this.requiredSuit = requiredSuit;
    }
}
