package ui;

import javax.swing.*;
import java.awt.*;

// Represents the score panel
public class ScorePanel extends JPanel {
    private static final int WIDTH = 150;
    private static final int HEIGHT = 20;
    private AvoidPoopGame avoidPoopGame;
    private JLabel scoreLabel;

    // Constructs a score panel
    // EFFECTS: sets up the panel with "TURN COUNT" and actual turn count from the game
    public ScorePanel(AvoidPoopGame avoidPoopGame) {
        this.avoidPoopGame = avoidPoopGame;
        setBackground(new Color(0, 150, 200));
        scoreLabel = new JLabel("TURN COUNT: " + avoidPoopGame.getTurnCount());
        scoreLabel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(scoreLabel);
    }

    // MODIFIES: this
    // EFFECTS: updates the turn count from the game state
    public void update() {
        scoreLabel.setText("TURN COUNT: " + avoidPoopGame.getTurnCount());
        repaint();
    }
}
