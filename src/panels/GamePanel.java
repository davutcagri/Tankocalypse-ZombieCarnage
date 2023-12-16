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
import java.util.Random;

public class GamePanel extends JPanel {
    private final int originX, originY;
    private Character character;
    private BufferedImage map;
    private JLabel waveLabel, scoreLabel, healthLabel;
    private final List<Bullet> bullets = new ArrayList<>();
    private final List<Enemy> enemies = new ArrayList<>();
    private int score;
    private int healthReductionAmount = 10;
    private int wave = 1;

    public GamePanel() {
        this.originX = 800 / 2;
        this.originY = 700 / 2;
        this.addMouseMotionListener(new GameMouseListener());
        this.addMouseListener(new GameMouseListener());
        this.addKeyListener(new GameKeyboardListener());
        init();
    }

    private void init() {
        character = new Character(originX, originY);
        generateEnemies();

        // Wave  Label
        waveLabel = new JLabel("Wave: " + wave);
        waveLabel.setFont(new Font("Arial", Font.BOLD, 24));
        waveLabel.setForeground(Color.blue);
        this.add(waveLabel);

        // Score Label
        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scoreLabel.setForeground(Color.orange);
        this.add(scoreLabel);

        // Health Label
        healthLabel = new JLabel("Health: %" + character.getHealth());
        healthLabel.setFont(new Font("Arial", Font.BOLD, 24));
        healthLabel.setForeground(Color.red);
        this.add(healthLabel);

        loadMapTexture();
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

    private void generateEnemies() {
        int maxX = 800, maxY = 700;
        Random random = new Random();
        int count = 0;
        switch (wave) {
            case 1: {
                count = 5;
                break;
            }
            case 2: {
                count = 7;
                break;
            }
            case 3: {
                count = 10;
                break;
            }
            case 4: {
                count = 15;
                break;
            }
            case 5: {
                count = 17;
                break;
            }
        }
        for (int i = 0; i < count; i++) {
            int side = random.nextInt(4); // Choose random side
            int x;
            int y;

            switch (side) {
                case 0: // Left Side
                    x = random.nextInt(maxX);
                    y = -random.nextInt(100);
                    break;
                case 1: // Right Side
                    x = maxX + random.nextInt(100);
                    y = random.nextInt(maxY);
                    break;
                case 2: // Bottom Side
                    x = random.nextInt(maxX);
                    y = maxY + random.nextInt(100);
                    break;
                case 3: // Right Side
                    x = -random.nextInt(100);
                    y = random.nextInt(maxY);
                    break;
                default:
                    x = random.nextInt(maxX);
                    y = random.nextInt(maxY);
            }
            Enemy enemy = new Enemy(x, y , character.getX(), character.getY());
            enemies.add(enemy);
            enemy.start();
        }
        startEnemyTimer();
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
                    ((Timer) e.getSource()).stop(); // Stop timer
                }
            }
        });
        bulletTimer.start();
    }

    private void startNewWave() {
        if(wave == 5) {
            
        } else {
            wave++;
            generateEnemies();
            waveLabel.setText("Wave: " + wave);
        }
    }

    private void checkCharacterEnemyCollision() {
        Rectangle characterR = character.getBounds();
        Iterator<Enemy> enemyIterator = enemies.iterator();
        while (enemyIterator.hasNext()) {
            Enemy enemy = enemyIterator.next();
            Rectangle enemyR = enemy.getBounds();
            if (characterR.intersects(enemyR)) {
                enemy.exit();
                enemyIterator.remove();
                character.reduceHealth(healthReductionAmount);
                healthLabel.setText("Health: %" + character.getHealth());
                waveLabel.setText("Wave: " + wave);
            }
        }
        if (enemies.isEmpty()) {
            startNewWave();
        }
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
                waveLabel.setText("Wave: " + wave);
            }
        }
        if (enemies.isEmpty()) {
            startNewWave();
        }
    }
}
