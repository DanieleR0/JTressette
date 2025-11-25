package model;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Card {

    private final CardSuit suit;
    private final CardValue value;
    private Image image; // memorizza l'immagine per riutilizzo

    public Card(CardSuit suit, CardValue value) {
        this.suit = suit;
        this.value = value;
        loadImage();
    }

    private void loadImage() {
        // qui non metti /model/ perché la cartella cards è nella root del source folder
        String fileName = "/cards/" + suit.getSuitName() + "_" + value.getCardName() + ".png";

        try (InputStream is = getClass().getResourceAsStream(fileName)) {
            if (is == null) {
                System.err.println("Immagine NON trovata: " + fileName);
                return; // evita crash
            }
            image = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public double getPointValue() {
    	return value.getPointValue();
    }

    // ------- GETTER ------- //

    public CardSuit getSuit() {
        return suit;
    }

    public CardValue getValue() {
        return value;
    }

    public Image getImage() {
        return image;
    }
}