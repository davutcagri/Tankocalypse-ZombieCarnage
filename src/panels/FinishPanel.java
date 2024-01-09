package panels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FinishPanel extends JPanel {

    private BufferedImage backgroundImage;

    public FinishPanel(String text, JFrame frame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Dikey yönde BoxLayout kullan

        try {
            backgroundImage = ImageIO.read(new File("D:\\work\\Tankocalypse-ZombieCarnage\\resource\\images\\background.jpg"));
        } catch (IOException e) {
            System.out.println("Start screen texture exception: " + e.getMessage());
        }

        this.add(Box.createVerticalStrut(200)); // 200x space

        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 72));
        label.setForeground(Color.white);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setAlignmentY(Component.CENTER_ALIGNMENT);
        this.add(label);

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
