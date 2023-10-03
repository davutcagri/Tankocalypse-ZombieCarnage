import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Character {
    private final int characterWidth = 90, characterHeight = 60;
    private int x, y;
    private double radian;
    private Image texture;

    public Character(int x, int y) {
        this.x = x;
        this.y = y;

        try {
            BufferedImage bufferedImage = ImageIO.read(new File("D:\\work\\TankVsZombie\\src\\images\\character.png"));
            texture = bufferedImage.getScaledInstance(characterWidth, characterHeight, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            System.out.println("Character texture error: " + e.getMessage());
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        float posX = x - characterWidth / 100f * 50;
        float posY = y - characterHeight / 100f * 50;

        g2d.rotate(radian, x, y);
        g2d.drawImage(texture, (int) posX, (int) posY, null);
    }

    public void rotate(int mouseX, int mouseY) {
        double angle = Math.toDegrees(Math.atan2(mouseY - y, mouseX - y));
        this.radian = Math.toRadians(angle);
    }

}
