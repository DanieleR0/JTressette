package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	
	private final List<Card> cards;
	
	public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
    }

	private void initializeDeck() {
		for (CardSuit suit : CardSuit.values()) {
            for (CardValue value : CardValue.values()) {
                cards.add(new Card(suit, value));
            }
        }
		
	}
	
	public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            return null; // oppure eccezione
        }
        return cards.remove(0);
    }

    public int size() {
        return cards.size();
    }
    
    public boolean isEmpty() {
        return cards.isEmpty();
    }
	
}
