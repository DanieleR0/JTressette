package controller;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class ScreenManager {

    private final JFrame frame;
    private final CardLayout layout;
    private final JPanel mainPanel;

    public ScreenManager(JFrame frame) {
        this.frame = frame;
        this.layout = new CardLayout();
        this.mainPanel = new JPanel(layout);
        frame.setContentPane(mainPanel);
    }

    public void addScreen(String name, JPanel panel) {
        mainPanel.add(panel, name);
    }

    public void show(String name) {
        layout.show(mainPanel, name);
    }
}
