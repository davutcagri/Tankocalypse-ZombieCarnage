import panels.GamePanel;
import panels.StartPanel;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private JPanel cardPanel;
    private final int screenWidth, screenHeight;

    public Window(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.setTitle("Tank vs Zombies");
        this.setSize(screenWidth, screenHeight);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        loadPanels();
        this.add(cardPanel);
        this.setVisible(true);
    }

    public void loadPanels() {
        CardLayout cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        StartPanel startPanel = new StartPanel(cardLayout, cardPanel);
        GamePanel gamePanel = new GamePanel(cardLayout, cardPanel, screenWidth, screenHeight);

        cardLayout.show(cardPanel, "startPage");
        cardPanel.add(startPanel, "startPage");
        cardPanel.add(gamePanel, "gamePage");
    }
}
