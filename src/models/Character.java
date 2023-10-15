package models;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Character extends Model{
    private final int characterWidth, characterHeight;
    private int x, y;
    private double radian;
    private Image texture;

    public Character(int x, int y) {
        this.x = x;
        this.y = y;
        this.characterWidth = 90;
        this.characterHeight = 60;

        try {
            BufferedImage bufferedImage = ImageIO.read(new File("D:\\work\\Tankocalypse-ZombieCarnage\\images\\character.png"));
            texture = bufferedImage.getScaledInstance(characterWidth, characterHeight, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            System.out.println("Character model texture exception: " + e.getMessage());
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.rotate(radian, x, y);
        g2d.drawImage(texture, (x - (characterWidth / 2)), (y - (characterHeight / 2)), null);
    }

    public void rotate(int mouseX, int mouseY) {
        double angle = Math.toDegrees(Math.atan2(mouseY - y, mouseX - x));
        this.radian = Math.toRadians(angle);
    }
}
