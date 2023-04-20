import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.*;

public class Main {

    private static void initUI() throws IOException {
        JFrame menu = new JFrame("Algoritmica Grafurilor");
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(1000, 1000);
        menu.setVisible(true);
        menu.add(new VisualGraph());
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable()
        {
            public void run() {
                try {
                    initUI();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }
}