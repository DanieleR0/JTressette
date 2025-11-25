package view;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    public MenuPanel(Runnable startGame) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton play = new JButton("Gioca");
        play.setFont(new Font("Arial", Font.BOLD, 28));
        play.addActionListener(e -> startGame.run());

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(play, gbc);
    }
}
