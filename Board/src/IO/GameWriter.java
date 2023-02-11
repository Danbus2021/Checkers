package IO;

import Model.Board;

import java.io.*;

public class GameWriter {
    Writer writer;
    //Board board;
    public GameWriter(String filename, boolean append){
        try {
            writer = new FileWriter(filename, append);
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    public void write(String data){
        try {
            writer.write(data);
            writer.flush();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
