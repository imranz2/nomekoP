import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.*;

public class GraphicsPanel extends JPanel implements ActionListener, KeyListener, MouseListener {
    private Clip musicClip;
    private Clip victoryClip;
    private boolean victoryPlayed = false;
    private BufferedImage background;
    private BufferedImage idle;
    private Timer timer;
    private boolean[] pressedKeys;
    private Player player;
    private Rectangle character;
    private int xBack = -780;
    private int yBack = -670;

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

    private int backWidth = 2700;
    private int backHeight = 1750;
    private static int num = 1;

    private boolean room1 = true;
    private boolean room2 = false;
    private boolean room3 = false;
    private boolean room4 = false;
    private boolean room5 = false;
    private boolean room6 = false;
    private boolean room7 = false;
    private boolean room8 = false;

    private long lastSpacePressTime = 0;
    private final int spaceCooldown = 500;

    public GraphicsPanel() {
        player = new Player();
        timer = new Timer(4, this);
        timer.start();
        character = new Rectangle(470, 200, 90, 90);
        //Rectangle house = new Rectangle(-665, -506, 1310, 735);
        door1 = new Rectangle(280, 80, 55, 55);
        door2 = new Rectangle(1010, 210, 55, 55);
        try {
            background = ImageIO.read(new File("yeah/src/background1.png"));
            idle = ImageIO.read(new File("yeah/src/Forward1.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        pressedKeys = new boolean[128];
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
        requestFocusInWindow();
        playMusic();
    }

    /*
    - -865, -560. One rect. Width = ended at -1180, -560. Height went down to -820
    - Backround rect. Start at -665, -560. moves to -1975, -1295

    - From -1598, -580. To -1905, -935

    - Door1; From -1060, -750 to -1115, -805
    - Door2; From -1790, -880 to -1845. -935
    */

    public void updateX(int num){
        xBack += num;
        leftSide += num;
        rightSide += num;
        house1Left += num;
        house1Right += num;
        house2Left += num;
        house2Right += num;
        door1X += num;
        door2X += num;
    }

    public void updateY(int num){
        yBack += num;
        topSide += num;
        bottomSide += num;
        house1Bottom += num;
        house2Bottom += num;
        house2Top += num;
        door1Y += num;
        door2Y += num;
    }

    private boolean isSpacePressed() {
        if (pressedKeys[32]) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastSpacePressTime >= spaceCooldown) {
                lastSpacePressTime = currentTime;
                return true;
            }
        }
        return false;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.drawImage(background, -1845, -935, 2700, 1750, null);
        g.drawImage(background, xBack, yBack, backWidth, backHeight, null);

        if (pressedKeys[65]) { //A
            g.drawImage(player.getLeft().getActiveFrame(), 470, 200, 90, 90, null);
            updateX(3);
            if (leftSide >= 470) {
                updateX(-3);
            }
        } else if (pressedKeys[68]) {
            g.drawImage(player.getRight().getActiveFrame(), 470, 200, 90, 90, null);
            updateX(-3);
            if (rightSide <= 560){
                updateX(3);
            }
        } else if (pressedKeys[87]) {
            g.drawImage(player.getBack().getActiveFrame(), 470, 200, 90, 90, null);
            updateY(3);
            if (topSide >= 200){
                updateY(-3);

            }
        } else if (pressedKeys[83]) {
            g.drawImage(player.getForward().getActiveFrame(), 470, 200, 90, 90, null);
            updateY(-3);
            if (bottomSide <= 290) {
                updateY(3);
            }
        } else if (room1 && door2.intersects(character) && isSpacePressed()){
            change(2);
            room1 = false;
            room2 = true;
        } else if (room1 && door2.intersects(character) && isSpacePressed()){
            reset();
        } else if (room2 && door1.intersects(character) && isSpacePressed()){
            change(3);
            room2 = false;
            room3 = true;
        } else if (room2 && door2.intersects(character) && isSpacePressed()){
            reset();
            room2 = false;
            room1 = true;
        } else if (room3 && door1.intersects(character) && isSpacePressed()){
            change(4);
            room3 = false;
            room4 = true;
        } else if (room3 && door2.intersects(character) && isSpacePressed()){
            reset();
            room3 = false;
            room1 = true;
        } else if (room4 && door2.intersects(character) && isSpacePressed()){
            change(5);
            room4 = false;
            room5 = true;
        } else if (room4 && door1.intersects(character) && isSpacePressed()){
            reset();
            room4 = false;
            room1 = true;
        } else if (room5 && door1.intersects(character) && isSpacePressed()){
            change(6);
            room5 = false;
            room6 = true;
        } else if (room5 && door2.intersects(character) && isSpacePressed()){
            reset();
            room5 = false;
            room1 = true;
        } else if (room6 && door2.intersects(character) && isSpacePressed()){
            change(7);
            room6 = false;
            room7 = true;
        } else if (room6 && door1.intersects(character) && isSpacePressed()){
            reset();
            room6 = false;
            room1 = true;
        } else if (room7 && door1.intersects(character) && isSpacePressed()){
            change(8);
            room7 = false;
            room8 = true;
            if (musicClip != null && musicClip.isRunning()) {
                musicClip.stop();
                musicClip.close();
            }

            if (!victoryPlayed) {
                playVictory();
                victoryPlayed = true;
            }
        } else if (room7 && door2.intersects(character) && isSpacePressed()){
            reset();
            room7 = false;
            room1 = true;
        } else {
            g.drawImage(idle, 470, 200, 90, 90, null);
        }

        door1 = new Rectangle(door1X, door1Y, 55, 55);
        door2 = new Rectangle(door2X, door2Y, 55, 55);

        if (200 < house1Bottom){
            if (house1Right < 470) { } else if (house1Left > 560){ }  else
                if (house1Left <= 470+90 && house1Left > 470){
                updateX(3);
            } else if (house1Right > 470+90 && house1Left < 470) {
                updateY(-3);
            } else if (house1Right >= 470) {
                updateX(-3);
            } else if (house1Left <= 470+90 && house1Left > 470){
                updateX(3);
            }
        }

        if (house2Left > 290){ } else if (house2Right < 200){ } else
            if (house2Left <= 290 && house2Left >= 200 && house2Top < 200 && house2Bottom > 290){
            updateX(3);
        } else if (house2Left < 200 && house2Right > 290){
            if (house2Top < 290 && house2Top > 200){
                updateY(3);
            } else if (house2Bottom < 290 && house2Bottom > 200){
                updateY(-3);
            }
        } else if (house2Right <= 290 && house2Right > 200 && house2Top < 200 && house2Bottom > 290){
            updateX(3);
        }
    }

    public void buzz() {
        File wrong = new File("yeah\\src\\buzzer.wav");
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(wrong);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void playVictory() {
        File audioFile = new File("yeah\\src\\victory.wav");
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            victoryClip = AudioSystem.getClip();
            victoryClip.open(audioStream);
            victoryClip.start();
            victoryClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    public void playMusic() {
        File audioFile = new File("yeah\\src\\music.wav");
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            musicClip = AudioSystem.getClip();
            musicClip.open(audioStream);
            musicClip.start();
            musicClip.loop(Clip.LOOP_CONTINUOUSLY); // repeats song
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void reset(){
        try {
            background = ImageIO.read(new File("yeah/src/background1.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        buzz();
    }

    public void change (int num){
        try {
            background = ImageIO.read(new File("yeah/src/background" + num  + ".png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
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