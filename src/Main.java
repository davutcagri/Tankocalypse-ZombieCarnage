import panels.StartPanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try{
                JFrame jFrame = new JFrame();
                jFrame.setTitle("Tank vs Zombies");
                jFrame.setSize(800, 700);
                jFrame.setResizable(false);
                jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                jFrame.setLocationRelativeTo(null);
                jFrame.setContentPane(new StartPanel(jFrame));
                jFrame.setVisible(true);
            } catch (Exception e) {
                System.err.println(e.fillInStackTrace());
            }
        });
    }
}