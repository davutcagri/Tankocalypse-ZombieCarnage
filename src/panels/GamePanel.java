package panels;

import models.Bullet;
import models.Character;
import models.Enemy;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GamePanel extends JPanel {
    private final int originX, originY;
    private Character character;
    private BufferedImage map;
    private int score, health = 100;
    private JLabel scoreLabel, healthLabel;
    private final List<Bullet> bullets = new ArrayList<>();
    private final List<Enemy> enemies = new ArrayList<>();

    public GamePanel(CardLayout cardLayout, JPanel cardPanel, int screenWidth, int screenHeight) {
        this.originX = screenWidth / 2;
        this.originY = screenHeight / 2;
        this.addMouseMotionListener(new GameMouseListener());
        this.addMouseListener(new GameMouseListener());
        this.addKeyListener(new GameKeyboardListener());
        init();
    }

    private void init() {
        character = new Character(originX, originY);
        addTestEnemies(); // Test düşmanlarını oluştur
        enemies.get(0).start(); // Test düşman thread'i başlatır
        // Score Label
        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(scoreLabel);

        // Health Label
        healthLabel = new JLabel("Health: %" + health);
        healthLabel.setFont(new Font("Arial", Font.BOLD, 24));
        healthLabel.setForeground(Color.red);
        this.add(healthLabel);

        loadMapTexture();
        startEnemyTimer();
    }

    private void addTestEnemies() {
        // Test Düşmanları
        enemies.add(new Enemy(100, character.getY(), character.getX(), character.getY()));
        enemies.add(new Enemy(character.getX(), 600, character.getX(), character.getY()));
        enemies.add(new Enemy(100, 100, character.getX(), character.getY()));
    }

    private void loadMapTexture() {
        try {
            map = ImageIO.read(new File("D:\\work\\Tankocalypse-ZombieCarnage\\images\\map.jpg"));
        } catch (IOException e) {
            System.out.println("Map texture exception: " + e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics g2 = g.create();
        drawMap(g2);
        drawBullets(g2);
        drawEnemies(g2);
        drawCharacter(g2);
    }

    private void drawMap(Graphics g) {
        if (map != null) {
            g.drawImage(map, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private void drawBullets(Graphics g) {
        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            bullet.draw(g);

            if (!bullet.isActive()) {
                bulletIterator.remove();
            }
        }
    }

    private void drawEnemies(Graphics g) {
        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }
    }

    private void drawCharacter(Graphics g) {
        character.draw(g);
    }

    private void checkCharacterEnemyCollision() {
        Rectangle characterR = character.getBounds();
        Iterator<Enemy> enemyIterator = enemies.iterator();
        while (enemyIterator.hasNext()) {
            Enemy enemy = enemyIterator.next();
            Rectangle enemyR = enemy.getBounds();
            if (characterR.intersects(enemyR)) {
                enemy.exit();
            }
        }
    }

    public class GameMouseListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            Bullet bullet = new Bullet(originX - 5, originY - 5, 10, e.getX(), e.getY());
            bullets.add(bullet);
            bullet.run();
            startBulletTimer(bullet);
        }

        public void mouseMoved(MouseEvent e) {
            character.rotate(e.getX(), e.getY());
            repaint();
        }
    }

    public static class GameKeyboardListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            // ESC Menü
        }
    }

    private void startEnemyTimer() {
        Timer enemyTimer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
                checkCharacterEnemyCollision();
            }
        });
        enemyTimer.start();
    }

    private void startBulletTimer(Bullet bullet) {
        Timer bulletTimer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
                checkBulletEnemyCollision(bullet);
                if (!bullet.isActive()) {
                    ((Timer) e.getSource()).stop(); // Kurşun inaktif olduğunda timer'ı durdur
                }
            }
        });
        bulletTimer.start();
    }

    private void checkBulletEnemyCollision(Bullet bullet) {
        Rectangle bulletR = bullet.getBounds();
        Iterator<Enemy> enemyIterator = enemies.iterator();
        while (enemyIterator.hasNext()) {
            Enemy enemy = enemyIterator.next();
            Rectangle enemyR = enemy.getBounds();
            if (bulletR.intersects(enemyR)) {
                enemyIterator.remove();
                bullets.remove(bullet);
                score++;
                scoreLabel.setText("Score: " + score);
            }
        }
    }
}
