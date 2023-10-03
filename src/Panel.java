import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Panel extends JPanel {

    private int screenWidth, screenHeight;
    private Character character;

    public Panel(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.addMouseMotionListener(new GameMouseMotionListener());
        this.addMouseListener(new GameMouseClickListener());

        character = new Character(screenWidth / 2, screenHeight / 2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        character.draw(g);
    }

    public class GameMouseMotionListener implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            character.rotate(e.getX(), e.getY());
            repaint();
        }
    }

    public class GameMouseClickListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}
