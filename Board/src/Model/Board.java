package Model;

import IO.GameReader;
import IO.GameWriter;

import java.util.*;
import java.io.*;

public class Board {
    protected int[][] board = new int[8][8];
    protected String[][] name = new String[8][8];
    private ArrayList<Checker> red = new ArrayList<Checker>();
    private ArrayList<Checker> blue = new ArrayList<Checker>();
    private Checker mainChecker;
    private int count_Blue = 0, count_Red = 0;
    private boolean player = false;//false-blue true-red

    public static final int EMPTY = 0;
    public static final int RED = 1;
    public static final int BLUE = 2;
    public static final int RED_QUEEN = 3;
    public static final int BLUE_QUEEN = 4;

    //private GameWriter writer;
   // public GameReader reader;
    public Board()  {
        mainChecker = new Checker(false, false);
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j ++)
                board[i][j] = EMPTY;

        int k = 1;
        for(int i = 0; i < 3; i ++){
            for(int j = k; j < 8; j += 2){
                red.add(new Checker(i, j, false, false));
                board[i][j] = RED;
            }
            if(k == 0) k = 1;
            else {
                k = 0;
            }
        }
        k = 0;
        for(int i = 5; i < 8; i ++){
            for(int j = k; j < 8; j += 2){
                blue.add(new Checker(i, j, true, false));
                board[i][j] = BLUE;
            }
            if(k == 0) k = 1;
            else {
                k = 0;
            }
        }

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j ++) {
                name[j][i] = (char)(i + 65) + "" + (j + 1);
                //System.out.print(i + " " + j + " = " + name[i][j] + " ");
            }

       // writer = new GameWriter("in.txt", true);
       // reader = new GameReader("in.txt", this);
    }

    public Board(boolean a) {
        mainChecker = new Checker(false, false);
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                board[i][j] = EMPTY;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setCountBlue(int c ){
        count_Blue = c;
    }

    public Checker getMainChecker(){
        return mainChecker;
    }
    public void giveChecker(Checker c){
        mainChecker = c;
    }

    private void removeChecker(Checker c) {
        board[c.getX()][c.getY()] = 0;

        if(c.getColor()){
            blue.remove(c);
        }
        else{
            red.remove(c);
        }
    }

    private void setChecker(Checker c) {
        if(c.getColor()){
            blue.add(c);
            board[c.getX()][c.getY()] = 2;
        }
        else{
            red.add(c);
            board[c.getX()][c.getY()] = 1;
        }
    }

    public ArrayList<Checker> getBlue() throws RuntimeException {
        if(blue == null){
            throw new RuntimeException("no Blue checkers");
        }
        return blue;
    }

    public ArrayList<Checker> getRed() throws RuntimeException {
        if(red == null){
            throw new RuntimeException("no Red checkers");
        }
        return red;
    }

    public void move(int x, int y) {
        //try{
           // FileWriter fw = new FileWriter("in.txt", true);
            if(isMove(mainChecker, x, y)) {
                if(mainChecker.getColor() == player) {
                    player=!player;
                    String col = "";
                    if (mainChecker.getColor() == false) {
                        col = "Red";
                    } else {
                        col = "Blue";
                    }
                    System.out.print(col + " : " + name[mainChecker.getX()][mainChecker.getY()] + " - ");
                  //  writer.write(mainChecker.getX() + " " + mainChecker.getY() + "  " + mainChecker.getColor() + " " + mainChecker.getQueen() + " ");

                    removeChecker(mainChecker);
                    mainChecker = new Checker(x, y, mainChecker.getColor(), mainChecker.getQueen());
                    setChecker(mainChecker);
                    //writer.write(x + " " + y + "\n");
                    //fw.flush();
                    System.out.println(name[x][y]);

                    if (isEnd(mainChecker)) {
                        mainChecker.setQueen(true);
                        if (mainChecker.getColor()) {
                            board[x][y] = 3;
                        } else {
                            board[x][y] = 4;
                        }
                    }
                }
            }

            else if(isEat(mainChecker, x, y)) {
               //if(mainChecker.getColor()==player) {
                 //   player = !player;
                    String col = "";
                    int x1;
                    int y1;
                    if(!mainChecker.getQueen()) {
                        x1 = (mainChecker.getX() - x) == 2 ? mainChecker.getX() - 1 : mainChecker.getX() + 1;
                        y1 = (mainChecker.getY() - y) == 2 ? mainChecker.getY() - 1 : mainChecker.getY() + 1;
                    }
                    else{
                        x1 = -1;
                        y1 = -1;
                        if (x > mainChecker.getX() && y > mainChecker.getY()) {
                            for (int i = 1; i < (x - mainChecker.getX()); i++) {
                                if (board[mainChecker.getX() + i][mainChecker.getY() + i] != 0) {
                                    x1 = mainChecker.getX() + i;
                                    y1 = mainChecker.getY() + i;
                                }
                            }
                        } else if (x > mainChecker.getX() && y < mainChecker.getY()) {
                            for (int i = 1; i < (x - mainChecker.getX()); i++) {
                                if (board[mainChecker.getX() + i][mainChecker.getY() - i] != 0) {
                                    x1 = mainChecker.getX() + i;
                                    y1=mainChecker.getY() - i;
                                }
                            }
                        } else if (x < mainChecker.getX() && y > mainChecker.getY()) {
                            for (int i = 1; i < (-x + mainChecker.getX()); i++) {
                                if (board[mainChecker.getX() - i][mainChecker.getY() + i] != 0) {
                                    x1 = mainChecker.getX() - i;
                                    y1 = mainChecker.getY() + i;
                                }
                            }
                        } else if (x < mainChecker.getX() && y < mainChecker.getY()) {
                            for (int i = 1; i < (-x + mainChecker.getX()); i++) {
                                if (board[mainChecker.getX() - i][mainChecker.getY() - i] != 0) {
                                    x1 = mainChecker.getX() - i;
                                    y1 = mainChecker.getY() - i;
                                }
                            }
                        }
                        if(x1==-1||y1==-1)
                            throw new RuntimeException("cannot eat this checker because it does not exist");
                    }
                    Boolean color = !(mainChecker.getColor());
                    if (color) {
                        for (int i = 0; i < blue.size(); i++) {
                            if (blue.get(i).getX() == x1 && blue.get(i).getY() == y1)
                                eat(blue.get(i));
                        }
                    } else {
                        for (int i = 0; i < red.size(); i++) {
                            if (red.get(i).getX() == x1 && red.get(i).getY() == y1)
                                eat(red.get(i));
                        }
                    }
                    if (mainChecker.getColor() == false) {
                        col = "Red";
                    } else {
                        col = "Blue";
                    }
                    //writer.write(mainChecker.getX() + " " + mainChecker.getY() + "  " + mainChecker.getColor() + " " + mainChecker.getQueen() + " ");
                    System.out.print(col + " : " + name[mainChecker.getX()][mainChecker.getY()] + " - ");
                    removeChecker(mainChecker);
                    mainChecker = new Checker(x, y, !(color), mainChecker.getQueen());
                    setChecker(mainChecker);
                    System.out.println(name[x][y]);
                   // writer.write(x + " " + y + "\n");
                   // fw.flush();
                    if(!mainChecker.getQueen()){
                        if (isEnd(mainChecker)) {
                            mainChecker.setQueen(true);
                            if (mainChecker.getColor()) {
                                board[x][y] = 3;
                            } else {
                                board[x][y] = 4;
                            }
                        }
                    }
              // }
            }

       // } catch(IOException e){}
    }

    public void printMainChecker(){
        System.out.println(mainChecker.getX() + " " + mainChecker.getY() + " " + mainChecker.getColor());
    }

    private boolean isEnd(Checker c) {   //Проверка на дамку
        if(c.getColor() == false) {
            if(c.getX() == 7)
                return true;
        }

        else {
            if(c.getX() == 0)
                return true;
        }

        return false;
    }


    private void eat(Checker c) {
        removeChecker(c);
        if(c.getColor() == false) {
            count_Red++;
        }
        else {
            count_Blue++;
        }
    }

    private boolean isEat(Checker c, int x, int y) {
            if (!c.getQueen()) {
                int x1, y1;
                if ((c.getX() - x) == 2) {
                    x1 = c.getX() - 1;
                } else if ((c.getX() - x == -2)) {
                    x1 = c.getX() + 1;
                } else
                    x1 = -1;

                if ((c.getY() - y) == 2) {
                    y1 = c.getY() - 1;
                } else if ((c.getY() - y == -2)) {
                    y1 = c.getY() + 1;
                } else
                    y1 = -1;

                if (x1 != -1 && y1 != -1) {
                    if (c.getColor()) {
                        if ((board[x][y] == 0) && board[x1][y1] == RED)
                            return true;
                    } else {
                        if ((board[x][y] == 0) && board[x1][y1] == BLUE)
                            return true;
                    }
                }

                return false;
            } else {
                if ((x > board.length) || (y > board.length))
                    return false;
                if (Math.abs(x - c.getX()) != Math.abs(y - c.getY()))
                    return false;
                if (x == c.getX() || y == c.getY()) {
                    return false;
                }
                if (x < 0 || y < 0)
                    return false;
                if (board[x][y] != 0)
                    return false;
                int count = 0;
                if (x > c.getX() && y > c.getY()) {
                    for (int i = 1; i < (x - c.getX()); i++) {
                        if (board[c.getX() + i][c.getY() + i] != 0)
                            count++;
                    }
                    if(count<=1)
                        return true;
                    else
                        return false;

                } else if (x > c.getX() && y < c.getY()) {
                    for (int i = 1; i < (x - c.getX()); i++) {
                        if (board[c.getX() + i][c.getY() - i] != 0)
                            count++;
                    }
                    if(count<=1)
                        return true;
                    else
                        return false;
                } else if (x < c.getX() && y > c.getY()) {
                    for (int i = 1; i < (-x + c.getX()); i++) {
                        if (board[c.getX() - i][c.getY() + i] != 0)
                            count++;
                    }
                    if(count<=1)
                        return true;
                    else
                        return false;
                } else if (x < c.getX() && y < c.getY()) {
                    for (int i = 1; i < (-x + c.getX()); i++) {
                        if (board[c.getX() - i][c.getY() - i] != 0)
                            count++;
                    }
                    if(count<=1)
                        return true;
                    else
                        return false;
                }
                return false;
            }
    }

    private boolean isMove(Checker c, int x1, int y1) {
            if (!c.getQueen()) {

                if (x1 > board.length || y1 > board.length)
                    return false;

                if (x1 < 0 || y1 < 0)
                    return false;

                if (board[x1][y1] != 0)
                    return false;

                if (x1 == c.getX() || y1 == c.getY()) {
                    return false;
                }

                if (c.getColor()) {
                    if (x1 < c.getX() - 1 || (y1 < (c.getY() - 1) || y1 > (c.getY() + 1)))
                        return false;

                    if (x1 > c.getX())
                        return false;
                } else {
                    if (x1 > (c.getX() + 1) || (y1 < (c.getY() - 1) || y1 > (c.getY() + 1)))
                        return false;

                    if (x1 < c.getX())
                        return false;
                }
                return true;
            } else {
                if ((x1 > board.length) || (y1 > board.length))
                    return false;
                if (Math.abs(x1 - c.getX()) != Math.abs(y1 - c.getY()))
                    return false;
                if (x1 == c.getX() || y1 == c.getY()) {
                    return false;
                }
                if (x1 < 0 || y1 < 0)
                    return false;
                if (board[x1][y1] != 0)
                    return false;

                if (x1 > c.getX() && y1 > c.getY()) {
                    for (int i = 1; i < (x1 - c.getX()); i++) {
                        if (board[c.getX() + i][c.getY() + i] != 0)
                            return false;
                    }
                } else if (x1 > c.getX() && y1 < c.getY()) {
                    for (int i = 1; i < (x1 - c.getX()); i++) {
                        if (board[c.getX() + i][c.getY() - i] != 0)
                            return false;
                    }
                } else if (x1 < c.getX() && y1 > c.getY()) {
                    for (int i = 1; i < (-x1 + c.getX()); i++) {
                        if (board[c.getX() - i][c.getY() + i] != 0)
                            return false;
                    }
                } else if (x1 < c.getX() && y1 < c.getY()) {
                    for (int i = 1; i < (-x1 + c.getX()); i++) {
                        if (board[c.getX() - i][c.getY() - i] != 0)
                            return false;
                    }
                }

                return true;
            }
    }

    public int isWin() {
        if(count_Red == 12) {
            return 1;
        }
        else if(count_Blue == 12) {
            return 2;
        }
        else
            return 3;

    }

    public int getSize() {
        return board.length;
    }

}

