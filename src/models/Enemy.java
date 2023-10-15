package models;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Enemy {
    private int x, y;
    private int speed;
    private double radian;
    private Image texture;
    private final int zombieWidth = 50, zombieHeight = 50;
    private final int targetX, targetY;

    public Enemy(int x, int y, int targetX, int targetY) {
        this.x = x;
        this.y = y;
        this.targetX = targetX;
        this.targetY = targetY;
        this.speed = 2000;

        rotate(targetX, targetY);

        try {
            BufferedImage bufferedImage = ImageIO.read(new File("D:\\work\\Tankocalypse-ZombieCarnage\\images\\zombie.png"));
            texture = bufferedImage.getScaledInstance(zombieWidth, zombieHeight, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            System.out.println("Zombie model texture exception: " + e.getMessage());
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.rotate(radian, x, y);
        g2d.drawImage(texture, (int) x - zombieWidth / 2, (int) y - zombieHeight / 2, null);
    }

    public void rotate(int targetX, int targetY) {
        double angle = Math.toDegrees(Math.atan2(targetY - y, targetX - x));
        this.radian = Math.toRadians(angle);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
