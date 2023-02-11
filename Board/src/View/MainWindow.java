package View;
import IO.GameReader;
import IO.GameWriter;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class MainWindow extends JFrame {

    private Board b = new Board();
    GameReader reader = new GameReader("in.txt", b);
    //boolean isContinue;
    public MainWindow(String title, boolean isContinue) {
        super(title);
        if(isContinue){
            reader.load();
            if(b.isWin()==2||b.isWin()==1){
                return;
            }
            BoardView panel = new BoardView(b);
            System.out.println(b);
            Container c = getContentPane();
            c.add(panel);
        }
        else{
            GameWriter f = new GameWriter("in.txt", false);
            BoardView panel = new BoardView(new Board());
            System.out.println(b);
            Container c = getContentPane();
            c.add(panel);
        }

        setSize(700, 700);
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setVisible(true);
    }

	/*public static void main(String[] args) {
        MainWindow a = new MainWindow("Menu");
    }*/
}