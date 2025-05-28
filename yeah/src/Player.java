import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
public class Player {
    private boolean facingForward;
    private boolean facingLeft;
    private boolean facingRight;
    private boolean facingBack;
    private Animation idleAnimation;
    private Animation runningAnimation;
    private boolean idle;
    private Animation forward;
    private Animation left;
    private Animation right;
    private Animation back;

    public Player() {
        idle = false;
        ArrayList<BufferedImage> images1 = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            String filename1 = "yeah/src/Forward" + i + ".png";
            try {
                images1.add(ImageIO.read(new File(filename1)));
                if (i == 2){
                    images1.add(ImageIO.read(new File("yeah/src/Forward1.png")));
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        ArrayList<BufferedImage> images2 = new ArrayList<>();
        for (int j = 1; j < 4; j++) {
            String filename2 = "yeah/src/Left" + j + ".png";
            try {
                images2.add(ImageIO.read(new File(filename2)));
                if (j == 2){
                    images2.add(ImageIO.read(new File("yeah/src/Left1.png")));
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        ArrayList<BufferedImage> images3 = new ArrayList<>();
        for (int k = 1; k < 4; k++) {
            String filename3 = "yeah/src/Right" + k + ".png";
            try {
                images3.add(ImageIO.read(new File(filename3)));
                if (k == 2){
                    images3.add(ImageIO.read(new File("yeah/src/Right1.png")));
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        ArrayList<BufferedImage> images4 = new ArrayList<>();
        for (int g = 1; g < 4; g++) {
            String filename4 = "yeah/src/Back" + g + ".png";
            try {
                images4.add(ImageIO.read(new File(filename4)));
                if (g == 2){
                    images4.add(ImageIO.read(new File("yeah/src/Back1.png")));
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        forward = new Animation(images1, 350);
        left = new Animation(images2, 350);
        right = new Animation(images3, 350);
        back = new Animation(images4, 350);

    }

    public Animation getForward() {
        return forward;
    }

    public Animation getLeft() {
        return left;
    }

    public Animation getRight() {
        return right;
    }

    public Animation getBack() {
        return back;
    }
}
