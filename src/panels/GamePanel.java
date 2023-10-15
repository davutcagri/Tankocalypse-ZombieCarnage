package panels;

import models.Bullet;
import models.Character;
import models.Enemy;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GamePanel extends JPanel {

    private final int originX, originY;
    private final Character character;
    private BufferedImage map;
    private int score;
    private JLabel scoreLabel;
    private List<Bullet> bullets = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();

    public GamePanel(CardLayout cardLayout, JPanel cardPanel, int screenWidth, int screenHeight) {
        this.originX = screenWidth / 2;
        this.originY = screenHeight / 2;
        this.addMouseMotionListener(new GameMouseMotionListener());
        this.addMouseListener(new GameMouseClickListener());

        character = new Character(originX, originY);
        Enemy enemy = new Enemy(originX - 300, originY, originX, originY);
        enemies.add(enemy);

        scoreLabel = new JLabel("Score: " + String.valueOf(score));
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(scoreLabel);

        try {
            map = ImageIO.read(new File("D:\\work\\Tankocalypse-ZombieCarnage\\images\\map.png"));
        } catch (IOException e) {
            System.out.println("Map texture exception: " + e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Map
        if (map != null) {
            g.drawImage(map, 0, 0, getWidth(), getHeight(), this);
        }

        //Bullets
        Graphics bulletG = g.create();
        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            bullet.draw(bulletG);

            if (!bullet.isActive()) {
                bulletIterator.remove();
            }
        }

        //Enemies
        Graphics enemyG = g.create();
        Iterator<Enemy> enemyIterator = enemies.iterator();
        while (enemyIterator.hasNext()) {
            Enemy enemy = enemyIterator.next();
            enemy.draw(enemyG);
        }

        //Character
        Graphics characterG = g.create();
        character.draw(characterG);
    }

    public class GameMouseMotionListener extends MouseAdapter {
        @Override
        public void mouseMoved(MouseEvent e) {
            character.rotate(e.getX(), e.getY());
            repaint();
        }
    }

    public class GameMouseClickListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            Bullet bullet = new Bullet(originX - 5, originY - 5, 10, e.getX(), e.getY());
            bullets.add(bullet);
            bullet.start();

            Timer timer = new Timer(16, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    repaint();
                }
            });
            timer.start();
        }
    }

}
