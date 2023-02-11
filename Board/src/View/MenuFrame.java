package View;
import Model.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class MenuFrame extends JFrame {
    public MenuFrame(String title) {
        super(title);
        ImagePanel a = new ImagePanel();
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JButton button = new JButton("play");
        button.setBounds(300,150,100,50);
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                MainWindow mw = new MainWindow("game", false);
            }
        });
        panel.add(button);

        JButton cont = new JButton("continue");
        cont.setBounds(300, 250, 100, 50);
        cont.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                MainWindow mw = new MainWindow("game", true);
            }
        });
        panel.add(cont);

        Container c = getContentPane();
        c.add(panel);
        c.add(button);
        c.add(cont);
        c.add(a);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 700);
        setVisible(true);
    }

    public class ImagePanel extends JPanel{

        private BufferedImage image;

        public ImagePanel() {
            try {
                image = ImageIO.read(new File("C:\\Users\\User\\Desktop\\Work\\JAVA\\Practice\\Checkers\\ch.jpg"));
            } catch (IOException ex) {
                System.out.println("Error");
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }

    public static void main(String[] args){
        MenuFrame m = new MenuFrame("menu");
    }

}
