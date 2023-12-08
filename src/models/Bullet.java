package models;

import java.awt.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Bullet implements Runnable {
    private int x, y;
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
        g2d.fillOval(x, y, r, r);
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

        ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(1);
        executor.scheduleAtFixedRate(() -> {
            if (active) {
                x += (int) stepX;
                y += (int) stepY;

                if (x > 800 || y > 700 || x < 0 || y < 0) {
                    active = false;
                    executor.shutdown();
                }
            }
        }, 0, 16, TimeUnit.MILLISECONDS);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, r, r);
    }
}
