package JUnitTest;
import Model.*;

import junit.framework.TestCase;

public class ModelTest extends TestCase {
    private Board board;

    protected void setUp() {
        board = new Board();
    }

    public void testIsWin() {
        board.setCountBlue(12);
        assertTrue(board.isWin() == 2); //Выиграли синие
    }

    public void testGetBlue() {
        board.getBlue();
    }

    public void testGetRed() {
        board.getRed();
    }

    public void testGetSize() {
        assertTrue(board.getSize() == 8);
    }

    public void testSetQueen() {
        Checker c = new Checker(0, 1, false, false);
        c.setQueen(true);
        assertTrue(c.getQueen() == true);
    }

    public void testGetQueen() {
        Checker c = new Checker(2, 2, false, false);
        assertEquals(c.getQueen(), false);
    }

    public void testGetX() {
        Checker c = new Checker(2, 2, false, false);
        int result = c.getX();
        assertTrue(result == 2);
    }

    public void testGetY() {
        Checker c = new Checker(2, 3, false, false);
        int result = c.getY();
        assertTrue(result == 3);
    }

    public void testGetColor() {
        Checker c = new Checker(2, 2, true, false);
        boolean result = c.getColor();
        assertEquals(result, true);
    }

    public void testDoubleMove() {  //Проверка хода два раза шашкой одного цвета
        Checker c = new Checker(2,3,false,false);
        board.giveChecker(c);
        board.move(3, 2);
        board.move(4, 1);
        assertEquals(board.getBoard()[4][1], 0);
    }

    public void testMoveBack() { // Проверка на то что шашка не ходит назад
        Checker c = new Checker(2,3,false,false);
        board.giveChecker(c);
        board.move(3, 2);
        Checker c1 = new Checker(5,2,true,false);
        board.giveChecker(c1);
        board.move(4, 1);
        Checker c2 = new Checker(3,2,false,false);
        board.giveChecker(c2);
        board.move(2, 3);
        assertEquals(board.getBoard()[2][3], 0);
    }

    public void testMoveQueen() { // Проверка на то что шашка становится дамкой
        board = new Board(true);
        Checker c = new Checker(6,1,false,false);
        board.giveChecker(c);
        board.move(7, 2);
        assertEquals(board.getMainChecker().getQueen(), true);
    }

    public void testEat() { // Проверка исчезает ли шашка после того как ее съели
        board = new Board(true);
        Checker c = new Checker(3,2,false,false);
        Checker c1 = new Checker(4,1,true,false);
        board.giveChecker(c);
        board.move(5, 0);
        assertEquals(board.getBoard()[4][1], 0);
    }

    public void testCollision() { // Проверка на то что шашки не могут походить друг на друга
        board = new Board(true);
        Checker c = new Checker(3,2,false,false);
        Checker c1 = new Checker(4,1,true,false);
        board.giveChecker(c);
        board.move(4,1);
        assertEquals(board.getBoard()[4][1], 1);
    }
}