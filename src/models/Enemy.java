package models;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Enemy extends Model{
    private int speed;
    private final int targetX, targetY;

    public Enemy(int x, int y, int targetX, int targetY) {
        super(x, y, 50, 50);
        this.targetX = targetX;
        this.targetY = targetY;
        this.speed = 2000;

        rotate(targetX, targetY);

        try {
            BufferedImage bufferedImage = ImageIO.read(new File("D:\\work\\Tankocalypse-ZombieCarnage\\images\\zombie.png"));
            Image texture = bufferedImage.getScaledInstance(super.getWidth(), super.getHeight(), Image.SCALE_DEFAULT);
            super.setTexture(texture);
        } catch (IOException e) {
            System.out.println("Zombie model texture exception: " + e.getMessage());
        }
    }

    Runnable r = new Runnable() {
        @Override
        public void run() {
            double dx = targetX - getX();
            double dy = targetY - getY();
            double distance = Math.sqrt(dx * dx + dy * dy);
            double stepX = (dx / distance) * speed / 1000.0;
            double stepY = (dy / distance) * speed / 1000.0;

            while (true) {
                double newX = getX() + stepX;
                double newY = getY() + stepY;
                setX((int) newX);
                setY((int) newY);

                if(getX() == targetX && getY() == targetY) {
                    break;
                }

                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    };

    public void start() {
        Thread thread = new Thread(r);
        thread.start();
    }
}
