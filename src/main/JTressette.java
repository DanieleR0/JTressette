package main;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import controller.GameController;
import view.GamePanel;
import model.*;

public class JTressette {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            List<Player> players = new ArrayList<>();
            players.add(new HumanPlayer(new UserProfile("Giocatore")));
            players.add(new AIPlayer("AI1"));
            players.add(new AIPlayer("AI2"));

            GamePanel panel = new GamePanel(null);  // controller ancora non creato
            GameController controller = new GameController(players, panel);
            panel.setController(controller);
            panel.refresh(); // aggiorna subito tutte le mani e il tavolo

            JFrame frame = new JFrame("Tressette");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(panel);
            frame.setSize(900, 600);
            frame.setVisible(true);

            controller.startGame();
        });
    }
}