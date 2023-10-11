import panels.GamePanel;
import panels.StartPanel;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private static CardLayout cardLayout;
    private static JPanel cardPanel;
    private StartPanel startPanel;
    private GamePanel gamePanel;
    private int screenWidth, screenHeight;

    public Window(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;


        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        startPanel = new StartPanel(cardLayout, cardPanel);
        gamePanel = new GamePanel(cardLayout, cardPanel, screenWidth, screenHeight);

        cardLayout.show(cardPanel,  "startPage");
        cardPanel.add(startPanel, "startPage");
        cardPanel.add(gamePanel, "gamePage");

        this.setTitle("Tank vs Zombies");
        this.setSize(screenWidth, screenHeight);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.add(cardPanel);
        this.setVisible(true);
    }

}