package models;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Character extends Model {

    private int health = 100;

    public Character(int x, int y) {
        super(x, y, 90, 60);

        try {
            BufferedImage bufferedImage = ImageIO.read(new File("D:\\work\\Tankocalypse-ZombieCarnage\\images\\character.png"));
            Image texture = bufferedImage.getScaledInstance(super.getWidth(), super.getHeight(), Image.SCALE_DEFAULT);
            super.setTexture(texture);
        } catch (IOException e) {
            System.out.println("Character model texture exception: " + e.getMessage());
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void reduceHealth(int amount) {
        health -= amount;
        if (health < 0) {
            health = 0;
        }
    }
}
