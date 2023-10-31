package models;

import java.awt.*;

public class Bullet extends Thread {
    private double x, y;
    private final int r;
    private final int targetX, targetY;
    private final int speed;
    private boolean active = true;

    public Bullet(int x, int y, int r, int targetX, int targetY) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.targetX = targetX;
        this.targetY = targetY;
        this.speed = 7000;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.fillOval((int) x, (int) y, r, r);
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public void run() {
        double dx = targetX - x;
        double dy = targetY - y;
        double distance = Math.sqrt(dx * dx + dy * dy);
        double stepX = (dx / distance) * speed / 1000.0;
        double stepY = (dy / distance) * speed / 1000.0;

        while (true) {
            x += stepX;
            y += stepY;

            if(x > 800 || y > 700 || x < 0 || y < 0) {
                active = false;
                break;
            }

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
