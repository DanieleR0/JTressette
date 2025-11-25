package view;

import javax.swing.*;
import java.awt.*;

public class IntroPanel extends JPanel {

    public IntroPanel(Runnable onContinue) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("JTRESSETTE", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 42));

        JButton start = new JButton("Inizia");
        start.setFont(new Font("Arial", Font.PLAIN, 22));

        start.addActionListener(e -> onContinue.run());

        add(title, BorderLayout.CENTER);
        add(start, BorderLayout.SOUTH);
    }
}
