import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JPanel{

    public Menu(boolean oriented) {
        setBorder(BorderFactory.createLineBorder(Color.black));
        JFrame f = new JFrame("Algoritmica Grafurilor");
        if(oriented) {
            f.add(new MyPanel(true));
        }
        else f.add(new MyPanel(false));
        f.setSize(500, 500);
        f.setVisible(true);
    }
}