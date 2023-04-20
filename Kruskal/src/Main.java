import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.*;

public class Main {

    private static void initUI() throws IOException {
        JFrame menu = new JFrame("Algoritmica Grafurilor");
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Graph g = new Graph();
        menu.add(g);
        JPanel panel = new JPanel();
        JButton button = new JButton("Set cost arce");
        JButton cbutton = new JButton("Kruskal");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g.setCostArce();
            }
        });

        cbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g.Kruskal();
            }
        });
        panel.add(button);
        panel.add(cbutton);
        menu.add(panel, BorderLayout.NORTH);
        menu.setSize(1000, 1000);
        panel.setVisible(true);
        menu.setVisible(true);
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