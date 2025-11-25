package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    
    private final String nickname;
    protected final List<Card> hand;  // protected per accesso nelle sottoclassi
    
    private double points = 0.0;

    public Player(String nickname) {
        this.nickname = nickname;
        this.hand = new ArrayList<>();   // inizializzazione corretta
    }
    
    
    // --- LOGICA BASE ---
    
    public void receiveCard(Card c) {
        hand.add(c);
    }
    
    public boolean playCard(Card c) {
        return hand.remove(c);   // ritorna true se la carta era nella mano
    }
    
    /** 
     * Da implementare nelle sottoclassi:
     * - HumanPlayer: l'utente sceglie
     * - AIPlayer: logica automatica
     */
    public abstract Card chooseCardToPlay(CardSuit requiredSuit);
    
    public void addPoints(double points) {
        points += points;
    }
    

    // --- GETTER ---
    
    public String getNickname() {
        return nickname;
    }

    public List<Card> getHand() {
        return new ArrayList<>(hand); // copia per sicurezza
    }
    
    public double getPoints() {
        return points;
    }
}
