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
    private BufferedImage idle;
    private Timer timer;
    private boolean[] pressedKeys;
    private ArrayList<Pokemon> pokemon = new ArrayList<>(); //All pokemon 150+;
    private static int count = 1;
    private Player player;
    private Rectangle character;
    private int xBack = -780;
    private int yBack = -670;
    private boolean collide = false;
    private boolean touchingLeft;
    private boolean touchingRight;
    private boolean touchingTop;
    private boolean touchingBottom;
    private int leftSide = -115;
    private int rightSide = 1200;
    private int topSide = -110;
    private int bottomSide = 635;

    private int house1Bottom = 100;
    private int house1Left = 85;
    private int house1Right = 400;
    private Rectangle door1;
    private int door1X = 280;
    private int door1Y = 80;

    private int house2Bottom = 230;
    private int house2Left = 550;
    private int house2Right = 1195;
    private int house2Top = -120;
    private Rectangle door2;
    private int door2X = 1010;
    private int door2Y = 210;

    public GraphicsPanel() {
        player = new Player();
        timer = new Timer(4, this);
        timer.start();
        character = new Rectangle(470, 200, 90, 90);
        //Rectangle house = new Rectangle(-665, -506, 1310, 735);
        door1 = new Rectangle(280, 80, 55, 55);
        door2 = new Rectangle(1010, 210, 55, 55);
        try {
            background = ImageIO.read(new File("yeah/src/background.png"));
            idle = ImageIO.read(new File("yeah/src/Forward1.png"));
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

    /*
    - -865, -560. One rect. Width = ended at -1180, -560. Height went down to -820
    - Backround rect. Start at -665, -560. moves to -1975, -1295

    - From -1598, -580. To -1905, -935

    - Door1; From -1060, -750 to -1115, -805
    - Door2; From -1790, -880 to -1845. -935
    */

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, -1845, -935, 2700, 1750, null);

        //g.drawImage(background, xBack, -yBack, 2700, 1750, null);


        if (pressedKeys[65]) { //A
            g.drawImage(player.getLeft().getActiveFrame(), 470, 200, 90, 90, null);
            xBack += 3;
            leftSide += 3;
            rightSide += 3;
            house1Left += 3;
            house1Right += 3;
            house2Left += 3;
            house2Right += 3;
            door1 = new Rectangle(door1X + 3, door1Y, 55, 55);
            door2 = new Rectangle(door2X + 3, door2Y, 55, 55);
            if (leftSide >= 470) {
                door1 = new Rectangle(door1X - 3, door1Y, 55, 55);
                door2 = new Rectangle(door2X - 3, door2Y, 55, 55);
                house1Left -= 3;
                house1Right -= 3;
                house2Left -= 3;
                house2Right -= 3;
                rightSide -= 3;
                leftSide -= 3;
                xBack -= 3;
            }
        } else
        // player moves right (D)
        if (pressedKeys[68]) {
            g.drawImage(player.getRight().getActiveFrame(), 470, 200, 90, 90, null);
            xBack -= 3;
            leftSide -= 3;
            rightSide -= 3;
            house1Left -= 3;
            house1Right -= 3;
            house2Left -= 3;
            house2Right -= 3;
            door1 = new Rectangle(door1X - 3, door1Y, 55, 55);
            door2 = new Rectangle(door2X - 3, door2Y, 55, 55);
            if (rightSide <= 560){
                door1 = new Rectangle(door1X + 3, door1Y, 55, 55);
                door2 = new Rectangle(door2X + 3, door2Y, 55, 55);
                house1Left += 3;
                house1Right += 3;
                house2Left += 3;
                house2Right += 3;
                leftSide += 3;
                rightSide += 3;
                xBack += 3;
            }
        } else

        // player moves up (W)
        if (pressedKeys[87]) {
            g.drawImage(player.getBack().getActiveFrame(), 470, 200, 90, 90, null);
            yBack += 3;
            topSide += 3;
            bottomSide += 3;
            house1Bottom += 3;
            house2Bottom += 3;
            house2Top += 3;
            door1 = new Rectangle(door1X, door1Y+3, 55, 55);
            door2 = new Rectangle(door2X, door2Y+3, 55, 55);
            if (topSide >= 200){
                door1 = new Rectangle(door1X, door1Y-3, 55, 55);
                door2 = new Rectangle(door2X, door2Y-3, 55, 55);
                house1Bottom -=3;
                house2Bottom -=3;
                house2Top -= 3;
                topSide -= 3;
                bottomSide -= 3;
                yBack -= 3;
            }

        } else

        // player moves down (S)
        if (pressedKeys[83]) {
            g.drawImage(player.getForward().getActiveFrame(), 470, 200, 90, 90, null);
            yBack -= 3;
            bottomSide -= 3;
            topSide -= 3;
            house1Bottom -=3;
            house2Bottom -=3;
            house2Top -=3;
            door1 = new Rectangle(door1X, door1Y-3, 55, 55);
            door2 = new Rectangle(door2X, door2Y-3, 55, 55);
            if (bottomSide <= 290) {
                door1 = new Rectangle(door1X, door1Y+3, 55, 55);
                door2 = new Rectangle(door2X, door2Y+3, 55, 55);
                house1Bottom += 3;
                house2Bottom += 3;
                house2Top += 3;
                topSide += 3;
                bottomSide += 3;
                yBack += 3;
            }
        } else if (pressedKeys[32] && door1.intersects(character)) {
            try {
                background = ImageIO.read(new File("yeah/src/room1.png"));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            g.drawImage(idle, 470, 200, 90, 90, null);
        }


        if (200 < house1Bottom){
            if (house1Right < 470) {

            } else if (house1Left > 560){

            }  else if (house1Left <= 470+90 && house1Left > 470){
                xBack += 3;
                leftSide += 3;
                rightSide += 3;
                house1Left += 3;
                house1Right += 3;
                house2Left += 3;
                house2Right += 3;
            } else if (house1Right > 470+90 && house1Left < 470) {
                yBack -= 3;
                bottomSide -= 3;
                topSide -= 3;
                house1Bottom -= 3;
                house2Bottom -= 3;
                house2Top -=3;
            } else if (house1Right >= 470) {
                xBack -= 3;
                leftSide -= 3;
                rightSide -=3;
                house1Right -= 3;
                house1Left -= 3;
                house2Right -= 3;
                house2Left -=3;
            } else if (house1Left <= 470+90 && house1Left > 470){
                xBack += 3;
                leftSide += 3;
                rightSide += 3;
                house1Left += 3;
                house1Right += 3;
                house2Left += 3;
                house2Right += 3;
            }
        }


        if (house2Left > 290){

        } else if (house2Right < 200){

        } else if (house2Left <= 290 && house2Left >= 200 && house2Top < 200 && house2Bottom > 290){
            xBack += 3;
            leftSide += 3;
            rightSide += 3;
            house2Left += 3;
            house2Right += 3;
            house1Left += 3;
            house1Right += 3;
        } else if (house2Left < 200 && house2Right > 290){
            if (house2Top < 290 && house2Top > 200){
                yBack += 3;
                bottomSide += 3;
                topSide += 3;
                house1Bottom += 3;
                house2Bottom += 3;
                house2Top += 3;
            } else if (house2Bottom < 290 && house2Bottom > 200){
                yBack -= 3;
                bottomSide -= 3;
                topSide -= 3;
                house1Bottom -= 3;
                house2Bottom -= 3;
                house2Top -=3;
            }
        } else if (house2Right <= 290 && house2Right > 200 && house2Top < 200 && house2Bottom > 290){
            xBack -= 3;
            leftSide -= 3;
            rightSide -= 3;
            house1Right -= 3;
            house1Left -= 3;
            house2Right -= 3;
            house2Left -=3;
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        // see this for all keycodes: https://stackoverflow.com/questions/15313469/java-keyboard-keycodes-list
        // A = 65, D = 68, S = 83, W = 87, left = 37, up = 38, right = 39, down = 40, space = 32, enter = 10
        int key = e.getKeyCode();
        System.out.println(key);
        pressedKeys[key] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        pressedKeys[key] = false;
    }

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
