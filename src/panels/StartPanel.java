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

    public StartPanel(CardLayout cardLayout, JPanel cardPanel) {
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

        // START BUTTON
        JButton startButton = new JButton("Start");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        startButton.setMaximumSize(new Dimension(200, 50));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "gamePage");
            }
        });
        this.add(startButton);
        // END OF START BUTTON

        this.add(Box.createVerticalStrut(10)); // 10px space

        // SETTINGS BUTTON
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
        // END OF SETTINGS BUTTON

        this.add(Box.createVerticalStrut(10)); // 10px space

        // EXIT BUTTON
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
        // END OF EXIT BUTTON
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, 904, 700, this);
        }
    }
}
