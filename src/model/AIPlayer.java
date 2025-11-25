package model;

import java.util.List;
import java.util.stream.Collectors;

public class AIPlayer extends Player {

    public AIPlayer(String nickname) {
        super(nickname);
    }

    @Override
    public Card chooseCardToPlay(CardSuit requiredSuit) {

        // Filtra le carte del seme richiesto
        List<Card> sameSuit = hand.stream()
                .filter(c -> requiredSuit != null && c.getSuit() == requiredSuit)
                .collect(Collectors.toList());

        // Se esistono carte del seme richiesto, gioca la più bassa
        if (!sameSuit.isEmpty()) {
            return sameSuit.stream()
                    .min((a, b) -> Integer.compare(
                            a.getValue().getCardValue(),
                            b.getValue().getCardValue()))
                    .orElse(null);
        }

        // Se non può seguire il seme, gioca la carta più bassa
        return hand.stream()
                .min((a, b) -> Integer.compare(
                        a.getValue().getCardValue(),
                        b.getValue().getCardValue()))
                .orElse(null);
    }
    
    
}