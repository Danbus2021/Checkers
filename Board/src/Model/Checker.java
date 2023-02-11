package Model;


public class Checker {
    private int x, y;
    private boolean color;//0-red, 1-blue
    private boolean queen;

    public Checker(int x, int y, boolean c, boolean q){
        this.x = x;
        this.y = y;
        color = c;
        queen = q;
    }

    public Checker(boolean c, boolean q){
        this(-1,-1,c, q);
    }

    public void setX(int x1) {
        x = x1;
    }

    public void setY(int y1) {
        y = y1;
    }

    public void setQueen(boolean b) {
        queen = b;
    }

    public boolean getQueen() {
        return queen;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean getColor(){
        return color;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Checker){
            Checker c = (Checker)o;
            return (c.getX()==x && c.getY() == y && c.getColor() == color);
        }
        return false;
    }

}
