import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Graf
{
	private static void initUI() throws IOException {
        JFrame menu = new JFrame("Algoritmica Grafurilor");
        //sa se inchida aplicatia atunci cand inchid fereastra
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setez dimensiunea ferestrei
        menu.setSize(400, 400);
        //fac fereastra vizibila
        menu.setVisible(true);
        JButton nButton = new JButton("Neorientat");
        JButton oButton = new JButton("Orientat");
        menu.setLayout(new BoxLayout(menu.getContentPane(), BoxLayout.X_AXIS));
        menu.add(nButton);
        menu.add(oButton);
        menu.getRootPane().setDefaultButton(nButton);
        nButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.add(new Menu(false));
            }
        });
        oButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.add(new Menu(true));
            }
        });
    }

	public static void main(String[] args)
	{
		//pornesc firul de executie grafic
		//fie prin implementarea interfetei Runnable, fie printr-un ob al clasei Thread
		SwingUtilities.invokeLater(new Runnable() //new Thread()
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
