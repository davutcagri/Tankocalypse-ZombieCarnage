package models;

import java.awt.*;

public class Model {
    private int x, y;
    private double radian;
    private Image texture;
    private final int width, height;

    public Model(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.rotate(radian, x, y);
        g2d.drawImage(texture, (x - width / 2), (y - height / 2), null);
    }

    public void rotate(int mouseX, int mouseY) {
        double angle = Math.toDegrees(Math.atan2(mouseY - y, mouseX - x));
        radian = Math.toRadians(angle);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getRadian() {
        return radian;
    }

    public void setRadian(double radian) {
        this.radian = radian;
    }

    public Image getTexture() {
        return texture;
    }

    public void setTexture(Image texture) {
        this.texture = texture;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
