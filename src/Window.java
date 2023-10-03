import javax.swing.*;

public class Window extends JFrame {
    private final int screenWidth = 800, screenHeight = 700;
    private final Panel contentPanel = new Panel(screenWidth, screenHeight);

    public Window() {
        super("Tank vs Zombies");
        this.setSize(screenWidth, screenHeight);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setContentPane(contentPanel);
    }
}
