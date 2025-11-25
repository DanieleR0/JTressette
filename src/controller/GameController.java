package controller;

import model.*;
import logic.GameEngine;
import view.GamePanel;

import java.util.List;

public class GameController {

    private final GameEngine engine;
    private final GamePanel view;

    public GameController(List<Player> players, GamePanel view) {
        this.engine = new GameEngine(players);
        this.view = view;
    }

    public void startGame() {
        engine.startGame();
        view.refresh();
        handleAITurns();  // nel caso l'IA inizi per prima
    }

    public GameEngine getGameEngine() {
        return engine;
    }

    public void playHumanMove(Card card) {
        Player current = engine.getCurrentPlayer();
        if (!(current instanceof HumanPlayer)) return;

        boolean success = engine.playCard(current, card);
        if (success) {
            view.refresh();
            handleAITurns();
        }
    }

    private void handleAITurns() {
        while (engine.getCurrentPlayer() instanceof AIPlayer ai) {
            Card aiCard = ai.chooseCardToPlay(engine.getRequiredSuit());
            engine.playCard(ai, aiCard);
            view.refresh();
        }
    }
    
 // RITORNA IL GIOCATORE UMANO
    public Player getHumanPlayer() {
        return engine.getPlayers().stream()
                .filter(p -> p instanceof HumanPlayer)
                .findFirst()
                .orElse(null);
    }

    // RITORNA SOLO LE IA
    public List<Player> getAIPlayers() {
        return engine.getPlayers().stream()
                .filter(p -> p instanceof AIPlayer)
                .toList();
    }

    // CARTE SUL TAVOLO
    public List<Card> getPlayedCards() {
        return engine.getCardsOnTable();
    }
    
    
}