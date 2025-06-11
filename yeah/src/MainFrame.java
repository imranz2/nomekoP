import javax.swing.*;

public class MainFrame {
    public MainFrame() {
        JFrame frame = new JFrame("Chase the Rainbow!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        ImageIcon image = new ImageIcon(getClass().getResource("/pokemonRainbow.png"));

        frame.setIconImage(image.getImage());
        GraphicsPanel panel = new GraphicsPanel();
        frame.add(panel);

        frame.setVisible(true);


    }
}
