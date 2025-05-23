import javax.swing.*;

public class MainFrame {
    public MainFrame() {
        JFrame frame = new JFrame("Pokemon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        GraphicsPanel panel = new GraphicsPanel();
        frame.add(panel);
        frame.setVisible(true);
    }
}
