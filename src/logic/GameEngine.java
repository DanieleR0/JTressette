package logic;

import java.util.ArrayList;
import java.util.List;

import model.Card;
import model.CardSuit;
import model.Player;
import model.Deck;

public class GameEngine {

    private final List<Player> players;
    private final Deck deck;
    private int currentPlayerIndex;  // indice del giocatore di turno
    private CardSuit requiredSuit;   // seme obbligatorio della mano corrente
    private final List<Card> cardsOnTable; // carte giocate nella mano corrente

    public GameEngine(List<Player> players) {
        this.players = players;
        this.deck = new Deck();
        this.currentPlayerIndex = 0;
        this.cardsOnTable = new ArrayList<>();
    }

    // --- SETUP PARTITA ---
    public void startGame() {
        deck.shuffle();
        dealInitialCards();
        requiredSuit = null;
        cardsOnTable.clear();
    }

    private void dealInitialCards() {
        for (int i = 0; i < 10; i++) { // tipico numero di carte in Tressette
            for (Player p : players) {
                p.receiveCard(deck.drawCard());
            }
        }
    }

    // --- GETTERS ---
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public CardSuit getRequiredSuit() {
        return requiredSuit;
    }

    public List<Card> getCardsOnTable() {
        return new ArrayList<>(cardsOnTable);
    }

    // --- LOGICA DELLA PARTITA ---
    public boolean playCard(Player player, Card card) {
        // 1. Verifica turno
        if (player != getCurrentPlayer()) return false;

        // 2. Controllo seme obbligatorio
        if (requiredSuit != null && card.getSuit() != requiredSuit) {
            boolean hasRequired = player.getHand().stream()
                    .anyMatch(c -> c.getSuit() == requiredSuit);
            if (hasRequired) return false;
        }

        // 3. Gioca la carta
        player.playCard(card);
        cardsOnTable.add(card);

        // 4. Imposta seme obbligatorio se prima carta della mano
        if (requiredSuit == null) {
            requiredSuit = card.getSuit();
        }

        // 5. Controlla se la mano è completa
        if (cardsOnTable.size() == players.size()) {
            Player winner = determineTrickWinner();
            double points = calculateTrickPoints();
            winner.addPoints(points); // serve metodo addPoints in Player o UserProfile
            // Reset per la mano successiva
            cardsOnTable.clear();
            requiredSuit = null;
            currentPlayerIndex = players.indexOf(winner);
        } else {
            nextTurn();
        }

        return true;
    }

    private void nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    private Player determineTrickWinner() {
        Card maxCard = cardsOnTable.get(0);
        Player winner = players.get(currentPlayerIndex); // primo giocatore della mano
        CardSuit suit = maxCard.getSuit();

        for (int i = 1; i < cardsOnTable.size(); i++) {
            Card c = cardsOnTable.get(i);
            int playerIndex = (currentPlayerIndex + i) % players.size();
            if (c.getSuit() == suit && c.getValue().getCardValue() > maxCard.getValue().getCardValue()) {
                maxCard = c;
                winner = players.get(playerIndex);
            }
        }
        return winner;
    }

    private double calculateTrickPoints() {
        return cardsOnTable.stream()
                           .mapToDouble(Card::getPointValue) // ora funziona
                           .sum();
    }
    
    public List<Player> getPlayers() {
        return new ArrayList<>(players); // restituisce una copia per sicurezza
    }
}