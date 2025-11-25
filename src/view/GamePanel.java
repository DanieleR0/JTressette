package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.List;

import controller.GameController;
import model.Card;
import model.HumanPlayer;
import model.Player;

public class GamePanel extends JPanel {

    private GameController controller;

    private Image background;
    private Image backCard; // retro scalato
    private static final int CARD_WIDTH = 92;
    private static final int CARD_HEIGHT = 144;

    public GamePanel(GameController controller) {
        this.controller = controller;

        loadBackground();
        loadBackImage();

        setLayout(null);
        setPreferredSize(new Dimension(900, 600));

        // listener del mouse per cliccare sulle carte
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (controller == null) return; // sicurezza
                Card clicked = detectClickedCard(e.getX(), e.getY());
                if (clicked != null) {
                    controller.playHumanMove(clicked);
                }
            }
        });
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    // ---------------------- LOAD IMAGES ------------------------- //

    private void loadBackground() {
        try (InputStream is = getClass().getResourceAsStream("/images/background.png")) {
            if (is != null) {
                background = ImageIO.read(is);
            } else {
                background = new BufferedImage(900, 600, BufferedImage.TYPE_INT_ARGB);
                System.err.println("Background non trovato!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadBackImage() {
        try (InputStream is = getClass().getResourceAsStream("/images/back_card.png")) {
            if (is != null) {
                Image img = ImageIO.read(is);
                backCard = img.getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_SMOOTH);
            } else {
                backCard = new BufferedImage(CARD_WIDTH, CARD_HEIGHT, BufferedImage.TYPE_INT_ARGB);
                System.err.println("Retro carte non trovato!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------------- REFRESH ------------------------- //

    public void refresh() {
        repaint();
    }

    // ---------------------- PAINT ------------------------- //

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // disegno background
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        }

        drawAIHands(g);
        drawHumanHand(g);
        drawPlayedCards(g);
    }

    // ---------------------- DRAW METHODS ------------------------- //

    private void drawHumanHand(Graphics g) {
        HumanPlayer human = (HumanPlayer) controller.getHumanPlayer();
        if (human == null) return;

        List<Card> hand = human.getHand();
        int xStart = 80;
        int y = 400;

        for (int i = 0; i < hand.size(); i++) {
            Card c = hand.get(i);
            if (c.getImage() != null) {
                Image img = c.getImage().getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_SMOOTH);
                g.drawImage(img, xStart + i * (CARD_WIDTH + 10), y, null);
            }
        }
    }

    private void drawAIHands(Graphics g) {
        List<Player> aiPlayers = controller.getAIPlayers();
        int xStart = 80;
        int yStart = 50;

        for (Player ai : aiPlayers) {
            List<Card> hand = ai.getHand();
            for (int i = 0; i < hand.size(); i++) {
                g.drawImage(backCard, xStart + i * (CARD_WIDTH + 10), yStart, null);
            }
            yStart += CARD_HEIGHT + 20; // posizione verticale per il prossimo avversario
        }
    }

    private void drawPlayedCards(Graphics g) {
        List<Card> played = controller.getPlayedCards();
        int centerX = getWidth() / 2 - CARD_WIDTH / 2;
        int centerY = getHeight() / 2 - CARD_HEIGHT / 2;

        for (int i = 0; i < played.size(); i++) {
            Card c = played.get(i);
            if (c.getImage() != null) {
                Image img = c.getImage().getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_SMOOTH);
                g.drawImage(img, centerX + i * 30, centerY, null);
            }
        }
    }

    // ---------------------- MOUSE DETECTION ------------------------- //

    private Card detectClickedCard(int mouseX, int mouseY) {
        HumanPlayer human = (HumanPlayer) controller.getHumanPlayer();
        if (human == null) return null;

        List<Card> hand = human.getHand();
        int xStart = 80;
        int y = 400;

        for (int i = 0; i < hand.size(); i++) {
            int x1 = xStart + i * (CARD_WIDTH + 10);
            int x2 = x1 + CARD_WIDTH;
            if (mouseX >= x1 && mouseX <= x2 &&
                mouseY >= y && mouseY <= y + CARD_HEIGHT) {
                return hand.get(i);
            }
        }
        return null;
    }
}
