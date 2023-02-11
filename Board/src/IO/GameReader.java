package IO;

import Model.*;

import java.util.*;
import java.io.*;

public class GameReader {
    Board board;
    Reader reader;
    String data;
    public GameReader(String filename, Board b){
        try {
            reader = new FileReader(filename);
            int c = 0;
            char[] buffer = new char[1024];
            StringBuilder sb = new StringBuilder();
            while((c = reader.read(buffer)) >= 0){
                sb.append(String.valueOf(buffer, 0, c));
            }
            data = sb.toString();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        board = b;
    }

    public void load() {
        int i = 0, j = 0, x = 0, y = 0;
        boolean col = false, q = false;
        Scanner scanner = new Scanner(data);
        while(scanner.hasNext()) {
            String str = scanner.nextLine();
            Scanner scan = new Scanner(str);
            while(scan.hasNext()) {
                i = scan.nextInt();
                j = scan.nextInt();
                col = scan.nextBoolean();
                q = scan.nextBoolean();
                x = scan.nextInt();
                y = scan.nextInt();
            }
            Checker c = new Checker(i, j, col, q);
            board.giveChecker(c);
            board.move(x, y);
        }
    }
}
