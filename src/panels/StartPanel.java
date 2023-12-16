package panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StartPanel extends JPanel {
    private BufferedImage backgroundImage;
    private BufferedImage logoImage;

    public StartPanel(JFrame frame) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        try {
            backgroundImage = ImageIO.read(new File("D:\\work\\Tankocalypse-ZombieCarnage\\images\\background.jpg"));
            logoImage = ImageIO.read(new File("D:\\work\\Tankocalypse-ZombieCarnage\\images\\logo.png"));
            JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
            logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(logoLabel);
        } catch (IOException e) {
            System.out.println("Start screen texture exception: " + e.getMessage());
        }

        // Start Button
        JButton startButton = new JButton("Start Game");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        startButton.setMaximumSize(new Dimension(200, 50));
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new GamePanel());
                frame.invalidate();
                frame.validate();
            }
        });
        this.add(startButton);
        this.add(Box.createVerticalStrut(10)); // 10px space

        // Settings Button
        JButton settingsButton = new JButton("Settings");
        settingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        settingsButton.setFont(new Font("Arial", Font.BOLD, 24));
        settingsButton.setMaximumSize(new Dimension(200, 50));
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ayarlar ekranını gösterme kodu buraya gelebilir.
            }
        });
        this.add(settingsButton);
        this.add(Box.createVerticalStrut(10)); // 10px space

        // Exit Button
        JButton exitButton = new JButton("Exit");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setFont(new Font("Arial", Font.BOLD, 24));
        exitButton.setMaximumSize(new Dimension(200, 50));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.add(exitButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, 904, 700, this);
        }
    }
}
