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
    private static int count = 1;
    private Player player;

    public GraphicsPanel() {
        player = new Player();
        timer = new Timer(4, this);
        timer.start();
        try {
            background = ImageIO.read(new File("yeah/src/background.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        pokemon.add(new Pokemon("charizard", "fire", 170, "Flamethrower", 40, "Focus Blast", 90, "Roar", 30)); //90 dmg with 50% of missing, All jhave chanceo f missing except Scratch
        pokemon.add(new Pokemon("greninja", "water", 130, "Water Shuriken", 50, "Water Gun", 0, "Tackle", 25));
        pokemon.add(new Pokemon("goku", "everything", 1000, "Kamehameha", 75, "Kaioken", 0, "Spirit Bomb", 500)); // Spirit bomb with 10% chance, Kmehame with75% chance
        pressedKeys = new boolean[128];
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //
        g.drawImage(background, -300, -170, 1700, 880, null);
        g.drawImage(player.getForward().getActiveFrame(), 400, 200, 90, 90, null);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

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
