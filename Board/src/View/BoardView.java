package View;
import IO.GameWriter;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class BoardView extends JPanel {
    private Board board;
    GameWriter writer;

    public class Point{
        private int x;
        private int y;
        public Point(int xx, int yy){
            x = xx;
            y = yy;
        }
        public int getX(){
            return x;
        }
        public int getY(){
            return y;
        }
    }

    public Point findCoordinate(int x, int y){
        int px;
        int py;
        if(x >= 75 && x <= 475){
            if(y >= 75 && y <= 475){
                px = x - 75;
                py = y - 75;
            }
            else return null;
        }
        else return null;

        if(px < 50)
            px = 0;
        else if(px > 50 && px < 100)
            px = 1;
        else if(px > 100 && px < 150)
            px = 2;
        else if(px > 150 && px < 200)
            px = 3;
        else if(px > 200 && px < 250)
            px = 4;
        else if(px > 250 && px < 300)
            px = 5;
        else if(px > 300 && px < 350)
            px = 6;
        else if(px > 350 && px < 400)
            px = 7;

        if(py < 50)
            py = 0;
        else if(py > 50 && py < 100)
            py = 1;
        else if(py > 100 && py < 150)
            py = 2;
        else if(py > 150 && py < 200)
            py = 3;
        else if(py > 200 && py < 250){
            py = 4;
        }
        else if(py > 250 && py < 300)
            py = 5;
        else if(py > 300 && py < 350)
            py = 6;
        else if(py > 350 && py < 400)
            py = 7;

        Point p = new Point(px, py);
        return p;
    }
    public BoardView(Board b) {
        board = b;
        addMouseListener(new CheckerMouseListener());
        writer = new GameWriter("in.txt", true);
    }

    public class CheckerMouseListener extends MouseAdapter{
        public void mouseClicked(MouseEvent e){
            Point p = findCoordinate(e.getY(), e.getX());
            ArrayList<Checker> blue = board.getBlue();
            for(int i = 0; i < blue.size(); i++){
                if(blue.get(i).getX() == p.getX() && blue.get(i).getY() == p.getY()){
                    board.giveChecker(blue.get(i));
                    repaint();
                    return;
                }
            }
            ArrayList<Checker> red = board.getRed();
            for(int i = 0; i < red.size(); i++){
                if(red.get(i).getX() == p.getX() && red.get(i).getY() == p.getY()){
                    board.giveChecker(red.get(i));
                    repaint();
                    return;
                }
            }
            writer.write(board.getMainChecker().getX() + " " + board.getMainChecker().getY() + "  " + board.getMainChecker().getColor() + " " + board.getMainChecker().getQueen() + " ");
            board.move(p.getX(), p.getY());
            writer.write(p.getX()+" "+p.getY()+"\n");
            repaint();

            if(board.isWin() == 1){
                JOptionPane.showMessageDialog(new JDialog(),"blue is win");

                System.exit(1);
            }

            else if(board.isWin() == 2){
                JOptionPane.showMessageDialog(new JDialog(),"red is win");

                System.exit(2);
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Font font = new Font ("Arial", Font.PLAIN, 30);
        g.setColor(Color.gray);
        g.setFont (font);


        for(int i = 0; i < board.getSize(); i++){
            for(int j = 0; j < board.getSize(); j++){
                g.drawRect(75 + j * 50, 75 + i * 50, 50, 50);
            }
        }

        g.setColor(Color.black);
        for(int i = 0; i < board.getSize(); i++){
            for(int j = 0; j < board.getSize(); j++){
                String s = "" + (i + 1);
                g.drawString(s, 45, 110 + i * 50);
                String str = "" + (char)(i + 65);
                g.drawString(str, 90 + i * 50, 65);
            }
        }

        g.setColor(Color.gray);
        int k = 1;
        for(int i = 0; i < 8; i ++){
            for(int j = k; j < 8; j += 2){
                g.fillRect(75 + j * 50, 75 + i * 50, 50, 50);
            }
            if(k == 0) k = 1;
            else {
                k = 0;
            }
        }
        g.setColor(Color.yellow);
        g.drawOval(board.getMainChecker().getY()*50+89, board.getMainChecker().getX()*50+89, 22, 22);
        for(int i = 0; i < board.getBlue().size(); i++){
            g.setColor(Color.blue);
            if(board.getBlue().get(i).getQueen())
                g.drawOval(90 + board.getBlue().get(i).getY()*50,90+board.getBlue().get(i).getX()*50,20,20);
            else{
                g.fillOval(90 + board.getBlue().get(i).getY()*50,90+board.getBlue().get(i).getX()*50,20,20);
            }
        }
        for(int i = 0; i < board.getRed().size(); i++){
            g.setColor(Color.red);
            if(board.getRed().get(i).getQueen())
                g.drawOval(90 + board.getRed().get(i).getY()*50,90+board.getRed().get(i).getX()*50,20,20);
            else{
                g.fillOval(90 + board.getRed().get(i).getY()*50,90+board.getRed().get(i).getX()*50,20,20);
            }
        }

    }
}