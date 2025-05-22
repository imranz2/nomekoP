import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GraphicsPanel extends JPanel implements ActionListener, KeyListener, MouseListener {
    private BufferedImage background;
    private Timer timer;
    private boolean[] pressedKeys;
    private ArrayList<Pokemon> pokemon = new ArrayList<>(); //All pokemon 150+;

    public GraphicsPanel() {
        timer = new Timer(4, this);
        timer.start();
        try {
            background = ImageIO.read(new File("src/background.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        pokemon.add(new Pokemon("charizard", "fire", 170, "Flamethrower", 40, "Focus Blast", 90, "Scratch", 30)); //90 dmg with 50% of missing, All jhave chanceo f missing except Scratch
        pokemon.add(new Pokemon("greninja", ))
    }


    @Override
    public void actionPerformed(ActionEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
