import java.awt.*;
import java.util.Random;

public class Bullet {
    private int x, y, r;

    public Bullet(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Random rand = new Random();
        int testX = rand.nextInt((800 - 10) + 1);
        int testY = rand.nextInt((700 - 10) + 1);
        g2d.fillOval(testX, testY, r * 10, r * 10);
    }

    public void shoot() {

    }
}
